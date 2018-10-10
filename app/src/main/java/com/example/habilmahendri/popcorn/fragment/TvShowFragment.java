package com.example.habilmahendri.popcorn.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.fragment.fragmentTvShowR.TvShowsAiringTodayFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentTvShowR.TvShowsAnticipatedFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentTvShowR.TvShowsOnTheAirFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentTvShowR.TvShowsPopularFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentTvShowR.TvShowsTopRatedFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentTvShowR.TvShowsTrendingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    @BindView(R.id.tabs_tvShows)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_tvShows)
    ViewPager viewPager;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        ButterKnife.bind(this, view);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapterTvShows adapter = new ViewPagerAdapterTvShows(getChildFragmentManager());
        adapter.addFrag(new TvShowsTrendingFragment(), "TRENDING");
        adapter.addFrag(new TvShowsAnticipatedFragment(), "ANTICIPATED");
        adapter.addFrag(new TvShowsAiringTodayFragment(), "AIRING TODAY");
        adapter.addFrag(new TvShowsOnTheAirFragment(), "ON THE AIR");
        adapter.addFrag(new TvShowsPopularFragment(), "POPULAR");
        adapter.addFrag(new TvShowsTopRatedFragment(), "TOP RATED");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapterTvShows extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapterTvShows(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int i) {
            return mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
