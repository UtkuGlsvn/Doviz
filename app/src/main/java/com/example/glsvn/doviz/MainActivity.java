package com.example.glsvn.doviz;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.glsvn.doviz.model.dovizObject;
import com.example.glsvn.doviz.model.moneyApiInterface;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView clocktxt;
    TextView txt2,txt4,txt6;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save=findViewById(R.id.save);
        clocktxt=findViewById(R.id.clock);
        txt2 = findViewById(R.id.txt2);
        txt4 = findViewById(R.id.txt4);
        txt6 = findViewById(R.id.txt6);

        internetControl();


      new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                response();
            }
        },0,2000);

save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if ( isExternalStorageWritable() ) {
            save.setText(R.string.finish);
            File appDirectory = new File(Environment.getExternalStorageDirectory() + "/Mydovizapp");
            File logDirectory = new File(appDirectory + "/log");
            File logFile = new File(logDirectory, "logcat" + System.currentTimeMillis() + ".txt");

// create app folder
            if ( !appDirectory.exists() ) {
                appDirectory.mkdir();
            }

            // create log folder
            if ( !logDirectory.exists() ) {
                logDirectory.mkdir();
            }

            // clear the previous logcat and then write the new one to the file
            try {
                Process process = Runtime.getRuntime().exec("logcat -c");
                process = Runtime.getRuntime().exec("logcat -f " + logFile);
            } catch ( IOException e ) {
                e.printStackTrace();
            }

        }

    }
});

        }


    public void response() {
        moneyApiInterface myapiInterface = Api.getRetrofitInstance().create(moneyApiInterface.class);

        Call<dovizObject> call = myapiInterface.getDoviz();

        call.enqueue(new Callback<dovizObject>() {

            @Override
            public void onResponse(Call<dovizObject> call, Response<dovizObject> response) {
                dovizObject rates = response.body();

                String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                        .format(new Date(rates.getTimestamp() * 1000L));
                clocktxt.setText(date); //zamanı api den çektiğim için api de bulunan saat dilimine göre geliyor
                txt2.setText(rates.rates.getLira() + "  TL");
                txt4.setText(rates.rates.getUsd() + " USD");
                txt6.setText(rates.rates.getCad() + " CAD");
                Log.i("TL",rates.rates.getLira()+"");
                Log.i("USD",rates.rates.getUsd()+"");
                Log.i("CAD",rates.rates.getCad()+"");
                Log.d("response", response.toString());
            }

            @Override
            public void onFailure(Call<dovizObject> call, Throwable t) {
                Log.d("Error response", t.getMessage());
            }
        });
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if ( Environment.MEDIA_MOUNTED.equals( state ) ) {
            return true;
        }
        return false;
    }

    void internetControl()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected==false) {
            Toast.makeText(getBaseContext(), "İnternet bağlantısı yok!", Toast.LENGTH_SHORT).show();

        }

    }


}


/*

if ( isExternalStorageWritable() ) {

            File appDirectory = new File( Environment.getExternalStorageDirectory() + "/MyPersonalAppFolder" );
            File logDirectory = new File( appDirectory + "/log" );
            File logFile = new File( logDirectory, "logcat" + System.currentTimeMillis() + ".txt" );

            // create app folder
            if ( !appDirectory.exists() ) {
                appDirectory.mkdir();
            }

            // create log folder
            if ( !logDirectory.exists() ) {
                logDirectory.mkdir();
            }

            // clear the previous logcat and then write the new one to the file
            try {
                Process process = Runtime.getRuntime().exec("logcat -c");
                process = Runtime.getRuntime().exec("logcat -f " + logFile);
            } catch ( IOException e ) {
                e.printStackTrace();
            }

        } else if ( isExternalStorageReadable() ) {
            // only readable
        } else {
            // not accessible
        }
    }


    /* Checks if external storage is available to at least read */
  /*  public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if ( Environment.MEDIA_MOUNTED.equals( state ) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals( state ) ) {
            return true;
        }
        return false;
    }
 */