package com.example.glsvn.doviz.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface moneyApiInterface {

    @GET("latest?access_key=24ce3d397c383781c07bf42fc30b0241&symbols=TRY,USD,CAD&format=1/")
    Call<dovizObject> getDoviz();


}
//9d380ef95130dfc8266a7e633b41e8b6&symbols=TRY,USD,CAD&format=1
////http://data.fixer.io/api/latest?access_key=9d380ef95130dfc8266a7e633b41e8b6&symbols=TRY,USD,CAD&format=1