package com.example.habilmahendri.popcorn.fragment.fragmentDetailPeople;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.habilmahendri.popcorn.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowPeopleFragment extends Fragment {


    public TvShowPeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_people, container, false);
    }

}
