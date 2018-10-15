package com.example.habilmahendri.popcorn.fragment.fragmentDetailPeople;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.habilmahendri.popcorn.activity.DetailArtis;
import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.adapter.ImagePeopleAdapter;
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
public class InfoFragment extends Fragment {

    @BindView(R.id.overView)TextView overView;
    @BindView(R.id.born)
    TextView born;
    @BindView(R.id.birthplace)
    TextView birthplace;
    @BindView(R.id.name)TextView name;
    @BindView(R.id.rv_gallery)
    RecyclerView recyclerView;


    private ImagePeopleAdapter adapter;
    private Call<DataCatalog> apiCallDetail;
    private ApiClient apiClient = new ApiClient();
    String id;
    private ArrayList<DataCatalog>data;
    private Call<JSONResponse> apicall;
    private static final String TAG = "image people";

    Boolean tvOverview = true;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        DetailArtis activity = (DetailArtis) getActivity();
        id = activity.getMyData();


        overView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvOverview == true){
                    overView.setMaxLines(999);
                    tvOverview = false;
                }else if (tvOverview == false){
                    overView.setMaxLines(5);
                    tvOverview = true;
                }
                //overView.setEllipsize(z);
            }
        });

        ButterKnife.bind(this, view);

        getDataDetail();
        getImagePeople();

        return view;
    }


    private void getDataDetail() {
       apiCallDetail = apiClient.getApiCall().getDetailPeople(id);
        apiCallDetail.enqueue(new Callback<DataCatalog>() {
            @Override
            public void onResponse(Call<DataCatalog> call, Response<DataCatalog> response) {
                if (response.isSuccessful()) {
                    DataCatalog item = response.body();
                    overView.setText(item.getBiography());
                    born.setText(item.getBirthday());
                    birthplace.setText(item.getPlace_of_birth());
                    name.setText(item.getName());


                }
            }

            @Override
            public void onFailure(Call<DataCatalog> call, Throwable t) {
            }
        });
    }

    public void getImagePeople() {
        apicall = apiClient.getApiCall().getImagePeople(id);
        apicall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getProfiles()));

                for (int i = 0; i<data.size(); i++) {
                    DataCatalog p = data.get(i);
                    Log.i(TAG, "people image : " + p.getFile_path());
                }
                initView();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }

    private void initView() {

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImagePeopleAdapter(data, getActivity());
        recyclerView.setAdapter(adapter);

    }
}
