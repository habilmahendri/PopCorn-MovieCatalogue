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

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsTopRatedFragment extends Fragment {

    private ArrayList<DataCatalog> data;
    private TvShowsTrendingAdapter adapter;
    private ApiClient apiClient = new ApiClient();
    private Call<JSONResponse> apiCall;

    @BindView(R.id.rv_top_rated_tv_show)
    RecyclerView rvTopRatedTvShows;

    public TvShowsTopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_shows_top_rated, container, false);

        ButterKnife.bind(this, view);

        getTopRatedTvShows();

        return view;
    }

    private void getTopRatedTvShows() {
        apiCall = apiClient.getApiCall().getTopRatedTvShows();
        apiCall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));

                for (int i = 0; i < data.size(); i++) {
                    DataCatalog dataTopRated = data.get(i);

                    Log.i(TAG, "top rated title: " + dataTopRated.getOriginal_name());
                }
                adapter = new TvShowsTrendingAdapter(data, getContext());
                adapter.setTvShowItems(TvShowsTopRatedFragment.this.data);
                rvTopRatedTvShows.setHasFixedSize(true);
                rvTopRatedTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
                rvTopRatedTvShows.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }

}
