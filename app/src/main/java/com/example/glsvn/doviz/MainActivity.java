package com.example.glsvn.doviz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.glsvn.doviz.model.dovizObject;
import com.example.glsvn.doviz.model.moneyApiInterface;
import com.example.glsvn.doviz.model.ratesObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView clocktxt;
    Api myapi=new Api();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clocktxt=findViewById(R.id.clock);

        moneyApiInterface myapiInterface = Api.getRetrofitInstance().create(moneyApiInterface.class);

      /*  Call<dovizObject> call = myapiInterface.getDoviz();
        call.enqueue(new Callback<dovizObject>() {
            @Override
            public void onResponse(Call<dovizObject> call, Response<dovizObject> response) {
                ratesObject movies = response.body().getRates();
            }

            @Override
            public void onFailure(Call<dovizObject> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });*/
    }
}
