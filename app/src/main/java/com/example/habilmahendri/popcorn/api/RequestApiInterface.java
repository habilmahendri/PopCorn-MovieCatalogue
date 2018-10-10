package com.example.habilmahendri.popcorn.api;

import com.example.habilmahendri.popcorn.model.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestApiInterface {

    //Movie
    @GET("movie/now_playing")
    Call<JSONResponse> getNowPlaying();


    //TV Show
    @GET("tv/top_rated")
    Call<JSONResponse> getTrendingTvShows();

}
