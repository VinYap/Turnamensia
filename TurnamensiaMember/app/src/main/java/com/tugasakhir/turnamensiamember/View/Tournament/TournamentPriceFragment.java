package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentPriceFragment extends Fragment {
    private TextView mPrice1stTV;
    private TextView mPrice2ndTV;
    private TextView mPrice3rdTV;
    private TextView mPriceOtherTV;

    private Tournament mTournament;

    public TournamentPriceFragment() {
        // Required empty public constructor
    }

    public static TournamentPriceFragment newInstance(Tournament tournament) {
        TournamentPriceFragment fragment = new TournamentPriceFragment();
        fragment.mTournament = tournament;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tournament_price, container, false);

        mPrice1stTV = (TextView) view.findViewById(R.id.price_1st);
        mPrice2ndTV = (TextView) view.findViewById(R.id.price_2nd);
        mPrice3rdTV = (TextView) view.findViewById(R.id.price_3rd);
        mPriceOtherTV = (TextView) view.findViewById(R.id.price_other);

        mPrice1stTV.setText(mTournament.getPrize_1st());
        mPrice2ndTV.setText(mTournament.getPrize_2nd());
        mPrice3rdTV.setText(mTournament.getPrize_3rd());
        mPriceOtherTV.setText(mTournament.getPrize_other());

        return view;
    }

}
