package com.example.habilmahendri.popcorn.api;

import com.example.habilmahendri.popcorn.model.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestApiInterface {

    //Movie
    @GET("movie/now_playing")
    Call<JSONResponse> getNowPlaying();

    //Movie top box office
    @GET("movie/top_rated")
    Call<JSONResponse> getTopBox();

    //Movie upcoming
    @GET("movie/upcoming")
    Call<JSONResponse> getUpcoming(@Query("region")String region);

    //Movie trending
    @GET("trending/movie/week")
    Call<JSONResponse> getTrending();

    //Popular People
    @GET("person/popular")
    Call<JSONResponse> getPeople();

    //Search movie
    @GET("search/movie")
    Call<JSONResponse> getSearchMovie(@Query("query") String query);

    //Search people
    @GET("search/person")
    Call<JSONResponse> getSearchPeople(@Query("query") String query);

    //TV Show
    @GET("trending/{media_type}/{time_window}")
    Call<JSONResponse> getTrendingTvShows(@Path("media_type")String media_type, @Path("time_window")String time_window);

    @GET("tv/airing_today")
    Call<JSONResponse> getAiringTodayTvShows();

    @GET("tv/on_the_air")
    Call<JSONResponse> getOnTheAirTvShows();

    @GET("tv/popular")
    Call<JSONResponse> getPopularTvShows();

    @GET("tv/top_rated")
    Call<JSONResponse> getTopRatedTvShows();




}
