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

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsAiringTodayFragment extends Fragment {

    private ArrayList<DataCatalog> data;
    private TvShowsTrendingAdapter adapter;
    private Call<JSONResponse> apiCall;
    private ApiClient apiClient = new ApiClient();

    @BindView(R.id.rv_airing_today_tv_show)
    RecyclerView rvAiringTodayTvShows;


    public TvShowsAiringTodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_shows_airing_today, container, false);

        ButterKnife.bind(this, view);

        getAiringTodayTvShows();

        return view;
    }

    private void getAiringTodayTvShows() {
        apiCall = apiClient.getApiCall().getAiringTodayTvShows();
        apiCall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));

                for (int i = 0; i < data.size(); i++) {
                    DataCatalog dataAiringToday = data.get(i);

                    Log.i(TAG, "airing today title : " + dataAiringToday.getOriginal_name());
                }
                adapter = new TvShowsTrendingAdapter(data, getContext());
                adapter.setTvShowItems(TvShowsAiringTodayFragment.this.data);
                rvAiringTodayTvShows.setHasFixedSize(true);
                rvAiringTodayTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
                rvAiringTodayTvShows.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }


}
