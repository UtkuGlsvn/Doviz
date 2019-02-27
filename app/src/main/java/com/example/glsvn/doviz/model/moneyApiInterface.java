package com.example.glsvn.doviz.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface moneyApiInterface {

    @GET("latest?access_key=36c8e069629021bcc9eaec12c7fda5a6&symbols=TRY,USD,CAD&format=1/")
    Call<dovizObject> getDoviz();


}
