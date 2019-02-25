package com.example.glsvn.doviz.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ratesObject {

    @SerializedName("rates")
    private List<rateObject> movies;

    public List<rateObject> getMovies() {
        return movies;
    }

    public void setMovies(List<rateObject> movies) {
        this.movies = movies;
    }
}
