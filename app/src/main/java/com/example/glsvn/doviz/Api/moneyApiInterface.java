package com.example.glsvn.doviz.Api;

import com.example.glsvn.doviz.model.dovizObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface moneyApiInterface {

    @GET("latest?access_key=9d380ef95130dfc8266a7e633b41e8b6&symbols=TRY,USD,CAD&format=1/")
    Call<dovizObject> getDoviz();


}
