package com.example.habilmahendri.popcorn.fragment.fragmentMovieH;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.adapter.MovieAdapter;
import com.example.habilmahendri.popcorn.api.ApiClient;
import com.example.habilmahendri.popcorn.api.RequestApiInterface;
import com.example.habilmahendri.popcorn.model.DataCatalog;
import com.example.habilmahendri.popcorn.model.JSONResponse;


import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlaying extends Fragment {


    private MovieAdapter adapter;

    private ArrayList<DataCatalog>data;
    private Call<JSONResponse> apicall;
    private ApiClient apiClient = new ApiClient();
    private static final String TAG = "Nowplaying";

    @BindView(R.id.rv_now)
    RecyclerView recyclerView;



    public NowPlaying() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        // Inflate the layout for this fragment

        Toast.makeText(getContext(), "halo", Toast.LENGTH_SHORT).show();

        ButterKnife.bind(this, view);

        getNowPlaying();

        return view;
    }


    public void getNowPlaying() {
        apicall = apiClient.getApiCall().getNowPlaying();
        apicall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));

                for (int i = 0; i<data.size(); i++) {
                    DataCatalog p = data.get(i);
                    Log.i(TAG, "people : " + p.getName());
                }

                initView();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }
    private void initView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(data, getActivity());
        recyclerView.setAdapter(adapter);
    }


}
