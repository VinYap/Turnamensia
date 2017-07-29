package com.tugasakhir.turnamensiamember.View.MyTournament;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTournamentFragment extends Fragment {
    private RecyclerView mTournamentRV;

    public MyTournamentFragment() {
        // Required empty public constructor
    }

    public static MyTournamentFragment newInstance() {
        return new MyTournamentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_tournament, container, false);

        mTournamentRV = (RecyclerView) view.findViewById(R.id.my_tournament_rv);
        mTournamentRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTournamentRV.setHasFixedSize(true);

        MyTournamentAdapter adapter = new MyTournamentAdapter();
        mTournamentRV.setAdapter(adapter);

        return view;
    }

}
