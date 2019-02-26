package com.example.glsvn.doviz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.glsvn.doviz.model.dovizObject;
import com.example.glsvn.doviz.model.moneyApiInterface;

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

        moneyApiInterface myapiInterface = Api.getRetrofitInstance().create(moneyApiInterface.class);

        Call<dovizObject> call = myapiInterface.getDoviz();

        call.enqueue(new Callback<dovizObject>() {

            @Override
            public void onResponse(Call<dovizObject> call, Response<dovizObject> response) {
                dovizObject rates = response.body();

                txt2.setText(rates.rates.getLira()+"");
                txt4.setText(rates.rates.getUsd()+"");
                txt6.setText(rates.rates.getCad()+"");

                Log.d("response",response.toString());
            }

            @Override
            public void onFailure(Call<dovizObject> call, Throwable t) {
                Log.d("Error response", t.getMessage());
            }
        });
    }
}
