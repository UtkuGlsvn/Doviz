package com.example.glsvn.doviz.model;

import com.google.gson.annotations.SerializedName;

public class rateObject {
    @SerializedName("USD")
    private double usd;

    @SerializedName("TRY")
    private double lira;

    @SerializedName("CAD")
    private double cad;

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getLira() {
        return lira;
    }

    public void setLira(double lira) {
        this.lira = lira;
    }

    public double getCad() {
        return cad;
    }

    public void setCad(double cad) {
        this.cad = cad;
    }
}
