package com.example.habilmahendri.popcorn.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.adapter.ViewPagerAdapter;
import com.example.habilmahendri.popcorn.api.ApiClient;
import com.example.habilmahendri.popcorn.fragment.fragmentDetailPeople.TvShowPeopleFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentDetailPeople.InfoFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentMovieH.NowPlaying;
import com.example.habilmahendri.popcorn.model.DataCatalog;
import com.example.habilmahendri.popcorn.model.JSONResponse;
import com.example.habilmahendri.popcorn.slider.FragmentSlider;
import com.example.habilmahendri.popcorn.slider.SliderIndicator;
import com.example.habilmahendri.popcorn.slider.SliderPagerAdapter;
import com.example.habilmahendri.popcorn.slider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailArtis extends AppCompatActivity {

    private ArrayList<DataCatalog>data;
    private Call<DataCatalog> apiCallDetail;

    private ArrayList<String> image;
    private Call<JSONResponse> apiCall;
    private ApiClient apiClient = new ApiClient();

    private static final String TAG = "Nowplaying";
    private List<Fragment> fragments = new ArrayList<>();
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    @BindView(R.id.people_image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artis);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_title_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {

        }

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle("Name");

        getDataDetail();


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        getNowPlaying();
    }

    public void getNowPlaying() {
        String judul = getIntent().getStringExtra("id");
        apiCall = apiClient.getApiCall().getImage(judul);
        apiCall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                data = new ArrayList<>(Arrays.asList(jsonResponse.getResults()));

                for (int i = 0; i<5; i++) {
                    DataCatalog p = data.get(i);
                    String image = "https://image.tmdb.org/t/p/w500"+p.getFile_path();
                    Log.i(TAG, "get now playing : " + image);
                    fragments.add(FragmentSlider.newInstance(image));
                    mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
                    sliderView.setAdapter(mAdapter);
                }

                setupSlider();

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }


    private void getDataDetail() {
        final String judul = getIntent().getStringExtra("id");
        apiCallDetail = apiClient.getApiCall().getDetailPeople(judul);
        apiCallDetail.enqueue(new Callback<DataCatalog>() {
            @Override
            public void onResponse(Call<DataCatalog> call, Response<DataCatalog> response) {
                if (response.isSuccessful()) {
                    DataCatalog item = response.body();
                    Toast.makeText(DetailArtis.this, item.getName(), Toast.LENGTH_SHORT).show();
                    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
                    collapsingToolbarLayout.setTitle(item.getName());

                    Glide.with(getApplicationContext())
                            .load("http://image.tmdb.org/t/p/w185" + item.getProfile_path())
                            .into(imageView);
                }
            }

            @Override
            public void onFailure(Call<DataCatalog> call, Throwable t) {
            }
        });
    }

    public String getMyData() {
        String judul = getIntent().getStringExtra("id");
        return judul;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InfoFragment(), "INFO");
        adapter.addFragment(new NowPlaying(), "MOVIE");
        adapter.addFragment(new TvShowPeopleFragment(), "TV SHOWS");
        viewPager.setAdapter(adapter);
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

}

