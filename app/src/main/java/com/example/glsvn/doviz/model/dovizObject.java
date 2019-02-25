package com.example.glsvn.doviz.model;

import com.google.gson.annotations.SerializedName;

public class dovizObject {

    @SerializedName("rates")
    private ratesObject rates;
    @SerializedName("id")
    private String base;
    @SerializedName("date")
    private String date;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ratesObject getRates() {
        return rates;
    }

    public void setRates(ratesObject rates) {
        this.rates = rates;
    }
}
