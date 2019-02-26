package com.example.glsvn.doviz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.glsvn.doviz.model.dovizObject;
import com.example.glsvn.doviz.model.moneyApiInterface;

import java.security.Timestamp;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clocktxt=findViewById(R.id.clock);
        txt2 = findViewById(R.id.txt2);
        txt4 = findViewById(R.id.txt4);
        txt6 = findViewById(R.id.txt6);






      new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                response();
            }
        },0,2000);
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
                Log.d("response", response.toString());
            }

            @Override
            public void onFailure(Call<dovizObject> call, Throwable t) {
                Log.d("Error response", t.getMessage());
            }
        });
    }
}
