package com.example.glsvn.doviz.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface moneyApiInterface {
    @GET("symbols=TRY,USD,CAD&format=1")

    Call<dovizObject> getTopRatedMovies(@Query("9d380ef95130dfc8266a7e633b41e8b6&symbols") String apiKey);
}
//9d380ef95130dfc8266a7e633b41e8b6&symbols=TRY,USD,CAD&format=1";