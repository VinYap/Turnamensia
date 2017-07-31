package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.LiveMatch;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentLiveMatchFragment extends Fragment {
    private RecyclerView mLiveMatchRV;

    private List<LiveMatch> mLiveMatches;
    private String tournament_name;

    public TournamentLiveMatchFragment() {
        // Required empty public constructor
    }

    public static TournamentLiveMatchFragment newInstance(List<LiveMatch> liveMatches, String tournament_name) {
        TournamentLiveMatchFragment fragment = new TournamentLiveMatchFragment();
        fragment.mLiveMatches = liveMatches;
        fragment.tournament_name = tournament_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tournament_live_match, container, false);

        mLiveMatchRV = (RecyclerView) view.findViewById(R.id.tournament_live_match_rv);
        mLiveMatchRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mLiveMatchRV.setHasFixedSize(true);

        TournamentLiveMatchAdapter adapter = new TournamentLiveMatchAdapter(mLiveMatches, tournament_name);
        mLiveMatchRV.setAdapter(adapter);

        return view;
    }
}
