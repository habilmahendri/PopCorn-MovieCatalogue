package com.example.habilmahendri.popcorn.api;

import com.example.habilmahendri.popcorn.model.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestApiInterface {

    @GET("movie/now_playing")
    Call<JSONResponse> getNowPlaying();


}
