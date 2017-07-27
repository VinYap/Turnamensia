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
public class TournamentRuleFragment extends Fragment {
    private TextView mRuleTV;

    private String mRule;

    public TournamentRuleFragment() {
        // Required empty public constructor
    }

    public static TournamentRuleFragment newInstance(String rule) {
        TournamentRuleFragment fragment = new TournamentRuleFragment();
        fragment.mRule = rule;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tournament_rule, container, false);

        mRuleTV = (TextView) view.findViewById(R.id.tournament_rule);

        mRuleTV.setText(mRule);

        return view;
    }

}
