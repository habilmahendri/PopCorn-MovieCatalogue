package com.example.habilmahendri.popcorn.fragment.fragmentMovieH;


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
import com.example.habilmahendri.popcorn.adapter.PopularPeopleAdapter;
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
public class PopularPeopleFragment extends Fragment {

    private PopularPeopleAdapter adapter;

    private ArrayList<DataCatalog>data;
    private Call<JSONResponse> apicall;
    private ApiClient apiClient = new ApiClient();
    private static final String TAG = "Nowplaying";

    @BindView(R.id.rv_people)
    RecyclerView recyclerView;

    public PopularPeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        ButterKnife.bind(this, view);
        getPeople();
        // Inflate the layout for this fragment
        return view;
    }

    public void getPeople() {
        apicall = apiClient.getApiCall().getPeople();
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
        adapter = new PopularPeopleAdapter(data, getActivity());
        recyclerView.setAdapter(adapter);
    }


}
