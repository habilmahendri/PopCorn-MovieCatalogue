package com.example.habilmahendri.popcorn.fragment.fragmentMovieH;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.api.ApiClient;
import com.example.habilmahendri.popcorn.model.DataCatalog;
import com.example.habilmahendri.popcorn.model.JSONResponse;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlaying extends Fragment {

    private ArrayList<DataCatalog>data;
    private Call<JSONResponse> apicall;
    private ApiClient apiClient = new ApiClient();
    private static final String TAG = "Nowplaying";



    public NowPlaying() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        // Inflate the layout for this fragment
        getNowPlaying();

        return view;
    }
//
//    public void getNowPlaying() {
//        apicall = apiClient.getApiCall().getNowPlaying();
//        apicall.enqueue(new Callback<JSONResponse>() {
//            @Override
//            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
//                JSONResponse jsonResponse = response.body();
//                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));
//
//                for (int i = 0; i < data.size(); i++) {
//                    DataCatalog p = data.get(i);
//                    Log.i(TAG, "get now playing : " + p.getTitle());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<JSONResponse> call, Throwable t) {
//
//            }
//        });
//
//    }

    public void getNowPlaying() {
        apicall = apiClient.getApiCall().getNowPlaying();
        apicall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));

                for (int i = 0; i<data.size(); i++) {
                    DataCatalog p = data.get(i);
                    Log.i(TAG, "get now playing : " + p.getTitle());
                }

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }

}
