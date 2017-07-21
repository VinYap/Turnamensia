package com.tugasakhir.turnamensiamember.View.Organizer.Match;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Match;
import com.tugasakhir.turnamensiamember.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OMatchFragment extends Fragment {
    private RecyclerView mMatchRV;

    private List<Match> matches;

    public OMatchFragment() {
        // Required empty public constructor
    }

    public static OMatchFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);

        OMatchFragment f = new OMatchFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organizer_match, container, false);

        Bundle bundle = getArguments();

        mMatchRV = (RecyclerView) view.findViewById(R.id.match);
        mMatchRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mMatchRV.setHasFixedSize(true);

        initilizeData();

        OMatchAdapter adapter = new OMatchAdapter(matches);
        mMatchRV.setAdapter(adapter);
        return view;
    }

    private void initilizeData() {
        matches = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Match match = new Match();
            match.setMatchRound("Round 1");
            match.setMatchDate("21 Januari 2014 13:00");
            match.setRadiant("Radiant");
            match.setDire("Dire");
            match.setRadiantPhotoId(R.drawable.ib);
            match.setDirePhotoId(R.drawable.ib);
            match.setQrCodeId(R.drawable.ic_menu_gallery);
            matches.add(match);
        }

        Match match = new Match();
        match.setMatchRound("Round 1");
        match.setMatchDate("21 Januari 2014 13:00");
        match.setRadiant("Radiant Games");
        match.setDire("Dire Dr");
        match.setRadiantPhotoId(R.drawable.ib);
        match.setDirePhotoId(R.drawable.ib);
        match.setQrCodeId(R.drawable.ic_menu_gallery);
        matches.add(match);
    }
}
