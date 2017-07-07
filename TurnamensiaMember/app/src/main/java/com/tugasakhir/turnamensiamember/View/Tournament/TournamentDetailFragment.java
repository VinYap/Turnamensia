package com.tugasakhir.turnamensiamember.View.Tournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tugasakhir.turnamensiamember.Model.Basic.TournamentDetail;
import com.tugasakhir.turnamensiamember.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentDetailFragment extends Fragment {
    private ListView mDetailsLV;

    private List<TournamentDetail> details;

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
        View view = inflater.inflate(R.layout.fragment_tournament_detail, container, false);

        mDetailsLV = (ListView) view.findViewById(R.id.tournament_detail_lv);

        initializedData();

        TournamentDetailAdapter adapter = new TournamentDetailAdapter(getContext(), details);
        mDetailsLV.setAdapter(adapter);

        return view;
    }

    private void initializedData() {
        details = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            TournamentDetail detail = new TournamentDetail();
            details.add(detail);
        }
    }
}
