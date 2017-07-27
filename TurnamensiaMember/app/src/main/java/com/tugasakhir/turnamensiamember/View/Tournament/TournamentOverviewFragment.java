package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentOverviewFragment extends Fragment {
    private TextView mOverviewTV;

    private String mDescription;

    public TournamentOverviewFragment() {
        // Required empty public constructor
    }

    public static TournamentOverviewFragment newInstance(String description) {
        TournamentOverviewFragment fragment = new TournamentOverviewFragment();
        fragment.mDescription = description;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tournament_overview, container, false);

        mOverviewTV = (TextView) view.findViewById(R.id.tournament_overview);

        mOverviewTV.setText(mDescription);

        return view;
    }

}
