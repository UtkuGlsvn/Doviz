package com.example.glsvn.doviz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit;
    private static String url="http://data.fixer.io/api/latest?access_key=";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
//http://data.fixer.io/api/latest?access_key=9d380ef95130dfc8266a7e633b41e8b6&format=1
//http://data.fixer.io/api/latest?access_key=9d380ef95130dfc8266a7e633b41e8b6&symbols=TRY,USD,CAD&format=1