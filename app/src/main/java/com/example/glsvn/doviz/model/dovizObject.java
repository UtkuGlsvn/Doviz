package com.example.glsvn.doviz.model;

import com.google.gson.annotations.SerializedName;

public class dovizObject {

    @SerializedName("rates")
    public rateObject rates;
    @SerializedName("base")
    private String base;
    @SerializedName("date")
    private String date;
    @SerializedName("timestamp")
    private int timestamp;
    @SerializedName("success")
    private Boolean success;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

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

    public rateObject getRates() {
        return rates;
    }

    public void setRates(rateObject rates) {
        this.rates = rates;
    }
}
