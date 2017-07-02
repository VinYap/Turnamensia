package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentDetailFragment extends Fragment {

    public TournamentDetailFragment() {
        // Required empty public constructor
    }

    public static TournamentDetailFragment newInstance() {
        return new TournamentDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournament_detail, container, false);
    }

}
