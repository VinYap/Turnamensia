package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tugasakhir.turnamensiamember.Model.Basic.TournamentPrice;
import com.tugasakhir.turnamensiamember.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentPriceFragment extends Fragment {
    private ListView mPriceLV;

    private List<TournamentPrice> mPrices;

    public TournamentPriceFragment() {
        // Required empty public constructor
    }

    public static TournamentPriceFragment newInstance() {
        return new TournamentPriceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tournament_price, container, false);

        mPriceLV = (ListView) view.findViewById(R.id.tournament_price_lv);

        initializeData();

        TournamentPriceAdapter adapter = new TournamentPriceAdapter(getContext(), mPrices);
        mPriceLV.setAdapter(adapter);

        return view;
    }

    private void initializeData() {
        mPrices = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            TournamentPrice price = new TournamentPrice();

            mPrices.add(price);
        }
    }
}
