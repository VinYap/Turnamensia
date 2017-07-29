package com.tugasakhir.turnamensiamember.View.LiveMatch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchPlayer;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerStatFragment extends Fragment {
    private ImageView mRadiantIV;
    private TextView mRadiantTV;
    private RecyclerView mRadiantStatistic;
    private RecyclerView mRadiantItem;
    private ImageView mDireIV;
    private TextView mDireTV;
    private RecyclerView mDireStatistic;
    private RecyclerView mDireItem;

    private StatisticAdapter mRadiantStatisticAdapter;
    private ItemAdapter mRadiantItemAdapter;
    private StatisticAdapter mDireStatisticAdapter;
    private ItemAdapter mDireItemAdapter;

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

        mRadiantIV = (ImageView) view.findViewById(R.id.radiant_image_player_stat);
        mRadiantTV = (TextView) view.findViewById(R.id.radiant_text_player_stat);
        mRadiantStatistic = (RecyclerView) view.findViewById(R.id.radiant_statistic);
        mRadiantItem = (RecyclerView) view.findViewById(R.id.radiant_item);
        mDireIV = (ImageView) view.findViewById(R.id.dire_image_player_stat);
        mDireTV = (TextView) view.findViewById(R.id.dire_text_player_stat);
        mDireStatistic = (RecyclerView) view.findViewById(R.id.dire_statistic);
        mDireItem = (RecyclerView) view.findViewById(R.id.dire_item);

        mRadiantStatistic.setLayoutManager(new LinearLayoutManager(getContext()));
        mRadiantItem.setLayoutManager(new LinearLayoutManager(getContext()));
        mDireStatistic.setLayoutManager(new LinearLayoutManager(getContext()));
        mDireItem.setLayoutManager(new LinearLayoutManager(getContext()));

        mRadiantStatisticAdapter = new StatisticAdapter();
        mRadiantStatistic.setAdapter(mRadiantStatisticAdapter);
        mRadiantItemAdapter = new ItemAdapter();
        mRadiantItem.setAdapter(mRadiantItemAdapter);
        mDireStatisticAdapter = new StatisticAdapter();
        mDireStatistic.setAdapter(mDireStatisticAdapter);
        mDireItemAdapter = new ItemAdapter();
        mDireItem.setAdapter(mDireItemAdapter);

        return view;
    }

    public void updateStatistic(Integer team, List<Dota2LiveMatchPlayer> players) {
        if (team == 1) {
            mRadiantStatisticAdapter.setmPlayers(players);
            mRadiantStatisticAdapter.notifyDataSetChanged();
        } else if (team == 2) {
            mDireStatisticAdapter.setmPlayers(players);
            mDireStatisticAdapter.notifyDataSetChanged();
        }
    }

    public void updateItem(Integer team, List<Dota2LiveMatchPlayer> players) {
        if (team == 1) {
            mRadiantItemAdapter.setmPlayers(players);
            mRadiantItemAdapter.notifyDataSetChanged();
        } else if (team == 2) {
            mDireItemAdapter.setmPlayers(players);
            mDireItemAdapter.notifyDataSetChanged();
        }
    }

    public void updateImageHeader(Integer team, String image) {
        if (team == 1) {
            Picasso.with(getContext()).load(image).into(mRadiantIV);
        } else if (team == 2) {
            Picasso.with(getContext()).load(image).into(mDireIV);
        }
    }

    public void updateHeader(Integer team, String name) {
        if (team == 1) {
            mRadiantTV.setText(name);
        } else if (team == 2) {
            mDireTV.setText(name);
        }
    }
}
