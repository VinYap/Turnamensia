package com.tugasakhir.turnamensiamember.View.LiveMatch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerStatFragment extends Fragment {
    private RecyclerView mRadiant1RV;
    private RecyclerView mRadiant2RV;
    private RecyclerView mDire1RV;
    private RecyclerView mDire2RV;

    public PlayerStatFragment() {
        // Required empty public constructor
    }

    public static PlayerStatFragment newInstance() {
        return new PlayerStatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_stat, container, false);

        mRadiant1RV = (RecyclerView) view.findViewById(R.id.radiant_player_stat_1);
        mRadiant2RV = (RecyclerView) view.findViewById(R.id.radiant_player_stat_2);
        mDire1RV = (RecyclerView) view.findViewById(R.id.dire_player_stat_1);
        mDire2RV = (RecyclerView) view.findViewById(R.id.dire_player_stat_2);

        mRadiant1RV.setLayoutManager(new LinearLayoutManager(getContext()));
        mRadiant2RV.setLayoutManager(new LinearLayoutManager(getContext()));
        mDire1RV.setLayoutManager(new LinearLayoutManager(getContext()));
        mDire2RV.setLayoutManager(new LinearLayoutManager(getContext()));

        mRadiant1RV.setHasFixedSize(true);
        mRadiant2RV.setHasFixedSize(true);
        mDire1RV.setHasFixedSize(true);
        mDire2RV.setHasFixedSize(true);

        PlayerStat1Adapter radiant1Adapter = new PlayerStat1Adapter();
        PlayerStat2Adapter radiant2Adapter = new PlayerStat2Adapter();
        PlayerStat1Adapter dire1Adapter = new PlayerStat1Adapter();
        PlayerStat2Adapter dire2Adapter = new PlayerStat2Adapter();

        mRadiant1RV.setAdapter(radiant1Adapter);
        mRadiant2RV.setAdapter(radiant2Adapter);
        mDire1RV.setAdapter(dire1Adapter);
        mDire2RV.setAdapter(dire2Adapter);

        return view;
    }

}
