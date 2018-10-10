package com.example.habilmahendri.popcorn.fragment.fragmentTvShowR;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.adapter.TvShowsTrendingAdapter;
import com.example.habilmahendri.popcorn.api.ApiClient;
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
public class TvShowsTrendingFragment extends Fragment {

    private ArrayList<DataCatalog>data;
    private Call<JSONResponse> apicall;
    private ApiClient apiClient = new ApiClient();
    private static final String TAG = "Trending";

    @BindView(R.id.rv_trending_tv_show)
    RecyclerView rvTrending;
    TvShowsTrendingAdapter adapter;



    public TvShowsTrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_shows_trending, container, false);

        ButterKnife.bind(this, view);

        getTrending();

        return view;
    }

    public void getTrending() {
        apicall = apiClient.getApiCall().getTrendingTvShows();
        apicall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));

                for (int i = 0; i<data.size(); i++) {
                    DataCatalog trending = data.get(i);
                    Log.i(TAG, "get tranding : " + trending.getOriginal_name());
                }
                adapter = new TvShowsTrendingAdapter(data, getContext());
                adapter.setTvShowItems(TvShowsTrendingFragment.this.data);
                rvTrending.setHasFixedSize(true);
                rvTrending.setLayoutManager(new LinearLayoutManager(getContext()));
                rvTrending.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }

}
