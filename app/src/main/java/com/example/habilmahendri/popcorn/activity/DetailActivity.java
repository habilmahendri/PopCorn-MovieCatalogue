package com.example.habilmahendri.popcorn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.fragment.DiscoverFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentDetail.CastDetailFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentDetail.InfoDetailFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentDetail.ReviewDetailFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentDiscoverR.DiscoverMovieFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentDiscoverR.DiscoverTvShowsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {
//
//    @BindView(R.id.tabs)
//    TabLayout tabLayout;
//    @BindView(R.id.viewpagerDetail)
//    ViewPager viewPager;

    @BindView(R.id.tv_title_detail)
    TextView tvTitleDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        tvTitleDetail.setText("Venom");
        String title = tvTitleDetail.getText().toString();

//        setUpViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_title_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        toolbar.setTitle("Venom");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);
//        collapsingToolbarLayout.setTitle("Venom");

    }

//    private void setUpViewPager(ViewPager viewPager) {
//        ViewPagerAdapterDiscover adapterDiscover = new  ViewPagerAdapterDiscover(getSupportFragmentManager());
//        adapterDiscover.addFrag(new InfoDetailFragment(), "INFO");
//        adapterDiscover.addFrag(new CastDetailFragment(), "CAST");
//        adapterDiscover.addFrag(new ReviewDetailFragment(), "REVIEW");
//        viewPager.setAdapter(adapterDiscover);
//    }
//
//    private class ViewPagerAdapterDiscover extends FragmentPagerAdapter {
//
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapterDiscover(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int i) {
//            return mFragmentList.get(i);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFrag(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }
}
