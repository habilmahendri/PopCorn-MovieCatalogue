package com.example.habilmahendri.popcorn.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    //@BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.bind(this,view);

//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        // Inflate the layout for this fragment
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NowPlaying(),"Now Playing");
        adapter.addFragment(new TobBoxFragment(),"Top Box Office");
        adapter.addFragment(new UpComingFragment(), "Upcoming");
        adapter.addFragment(new TrendingFragment(), "Trending");
        viewPager.setAdapter(adapter);
    }

}
