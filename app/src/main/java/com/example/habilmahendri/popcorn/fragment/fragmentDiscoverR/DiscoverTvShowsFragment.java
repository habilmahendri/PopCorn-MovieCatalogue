package com.example.habilmahendri.popcorn.fragment.fragmentDiscoverR;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.adapter.MovieAdapter;
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

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverTvShowsFragment extends Fragment {

    private ArrayList<DataCatalog> data;
    private TvShowsTrendingAdapter adapter;
    private Call<JSONResponse> call;
    private ApiClient callApi = new ApiClient();

    @BindView(R.id.rv_discover_tvShow)
    RecyclerView rvDiscoverTvShow;


    public DiscoverTvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover_tv_shows, container, false);

        ButterKnife.bind(this, view);

        getDiscoverTvShow();

        return view;
    }

    private void getDiscoverTvShow() {
        call = callApi.getApiCall().getDiscoverTvShow();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));

                for (int i = 0; i < data.size(); i++) {
                    DataCatalog dataDiscoverTv = data.get(i);

                    Log.d(TAG, "get discover tv title : " + dataDiscoverTv.getOriginal_name());
                }
                adapter = new TvShowsTrendingAdapter(data, getContext());
                adapter.setTvShowItems(DiscoverTvShowsFragment.this.data);
                rvDiscoverTvShow.setHasFixedSize(true);
                rvDiscoverTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
                rvDiscoverTvShow.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }

}
