package com.example.glsvn.doviz;

import com.example.glsvn.doviz.model.moneyApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit=null;
    private static String url="http://data.fixer.io/api/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
//http://data.fixer.io/api/latest?access_key=9d380ef95130dfc8266a7e633b41e8b6&symbols=TRY,USD,CAD&format=1
