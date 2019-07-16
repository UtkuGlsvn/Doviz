package com.example.glsvn.doviz.Activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.glsvn.doviz.Api.Api;
import com.example.glsvn.doviz.R;
import com.example.glsvn.doviz.model.dovizObject;
import com.example.glsvn.doviz.Api.moneyApiInterface;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.savebtn) TextView save;
    @BindView(R.id.clocktxt) TextView clocktxt;
    @BindView(R.id.txtlira) TextView txtlira;
    @BindView(R.id.txtdolar) TextView txtdolar;
    @BindView(R.id.txtcandolar) TextView txtcandolar;

    Boolean control=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        internetControl();
        requestPermission();

      new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                response();
            }
        },0,2000);


}

    @OnClick(R.id.savebtn)
    public void savebtnClick() {

        if ( isExternalStorageWritable() && writeControl() && control) {
            control=false;
            save.setText(R.string.finish);
            File appDirectory = new File(Environment.getExternalStorageDirectory() + "/Mydovizapp");
            File logDirectory = new File(appDirectory + "/log");
            File logFile = new File(logDirectory, "logcat.txt");
            if ( !appDirectory.exists() ) {
                appDirectory.mkdir();
            }

            if ( !logDirectory.exists() ) {
                logDirectory.mkdir();
            }
            Toast.makeText(getBaseContext(),"Dosya oluştururdu adresi:"+appDirectory.toString(),Toast.LENGTH_SHORT).show();

            mylog(logFile);
        }
        else
        {
            save.setText(R.string.save);
            requestPermission();
            control=true;
        }

    }

    public void response() {
        moneyApiInterface myapiInterface = Api.getRetrofitInstance().create(moneyApiInterface.class);

        Call<dovizObject> call = myapiInterface.getDoviz();

        call.enqueue(new Callback<dovizObject>() {

            @Override
            public void onResponse(Call<dovizObject> call, Response<dovizObject> response) {
                dovizObject rates = response.body();
                if(rates.getSuccess()) {
                    String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                            .format(new Date((rates.getTimestamp()) * 1000L));
                    clocktxt.setText(date); //zamanı api den çektiğim için api de bulunan saat dilimine göre geliyor
                    txtlira.setText(new DecimalFormat("##.###").format(rates.rates.getLira()) + "  TL");
                    txtdolar.setText(new DecimalFormat("##.###").format(rates.rates.getUsd()) + " USD");
                    txtcandolar.setText(new DecimalFormat("##.###").format(rates.rates.getCad()) + " CAD");

                    Log.i("TL", rates.rates.getLira() + "");
                    Log.i("USD", rates.rates.getUsd() + "");
                    Log.i("CAD", rates.rates.getCad() + "");
                }
                else if (response.code()==104)
                    Toast.makeText(getBaseContext(),R.string.apirequestfull,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(),R.string.error,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<dovizObject> call, Throwable t) {
                Log.d("Error response", t.getMessage());
            }
        });
    }

    boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if ( Environment.MEDIA_MOUNTED.equals( state ) ) {
            return true;
        }
        return false;
    }

    boolean writeControl()
    {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v("writecontrol","Permission is granted");
            return true;
        }
        return false;
    }

    void mylog(final File logFile)
    {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("logcat -c");
            process = Runtime.getRuntime().exec("logcat -f " + logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void internetControl()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected==false) {
            Toast.makeText(getBaseContext(), R.string.notconnect, Toast.LENGTH_SHORT).show();

        }

    }
    private void requestPermission() {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

}
