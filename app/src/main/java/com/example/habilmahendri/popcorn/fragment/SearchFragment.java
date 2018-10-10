package com.example.habilmahendri.popcorn.fragment;


import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.adapter.ViewPagerAdapter;
import com.example.habilmahendri.popcorn.fragment.fragmentMovieH.NowPlaying;
import com.example.habilmahendri.popcorn.fragment.fragmentMovieH.TobBoxFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentMovieH.TrendingFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentMovieH.UpComingFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentSearchH.ActorSearchFragment;
import com.example.habilmahendri.popcorn.fragment.fragmentSearchH.MovieSearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MovieSearchFragment(),"Movie");
        adapter.addFragment(new ActorSearchFragment(),"Actors");

        viewPager.setAdapter(adapter);
    }


}
