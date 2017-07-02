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
public class TournamentLiveMatchFragment extends Fragment {


    public TournamentLiveMatchFragment() {
        // Required empty public constructor
    }

    public static TournamentLiveMatchFragment newInstance() {
        return new TournamentLiveMatchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournament_live_match, container, false);
    }

}
