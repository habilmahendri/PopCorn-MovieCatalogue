package com.example.habilmahendri.popcorn.model;

import com.google.gson.annotations.SerializedName;

public class DataCatalog {

    //Movie
    @SerializedName("poster_path")
    private String poster;

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("overview")
    private String overview;


    //TvShows
    @SerializedName("original_name")
    private String original_name;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("vote_average")
    private float vote_average;


    //People
    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profile_path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}