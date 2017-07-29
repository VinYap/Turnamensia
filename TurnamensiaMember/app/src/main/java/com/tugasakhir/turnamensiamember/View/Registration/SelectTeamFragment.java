package com.tugasakhir.turnamensiamember.View.Registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTeamFragment extends Fragment {
    private RecyclerView mTeamRV;
    private TextView mLeftActionTV;
    private TextView mRightActionTV;

    private List<Team> mTeams;
    private Tournament mTournament;
    private SelectTeamAdapter mAdapter;

    public SelectTeamFragment() {
        // Required empty public constructor
    }

    public static SelectTeamFragment newInstance(List<Team> teams, Tournament tournament) {
        SelectTeamFragment fragment = new SelectTeamFragment();
        fragment.mTeams = teams;
        fragment.mTournament = tournament;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_team, container, false);

        mTeamRV = (RecyclerView) view.findViewById(R.id.select_team);
        mLeftActionTV = (TextView) view.findViewById(R.id.left_action);
        mRightActionTV = (TextView) view.findViewById(R.id.right_action);

        mLeftActionTV.setText("Select Your Team");
        mRightActionTV.setText("Choose Your Player");

        mTeamRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTeamRV.setHasFixedSize(true);

        mAdapter = new SelectTeamAdapter(mTeams, mTournament);
        mTeamRV.setAdapter(mAdapter);

        return view;
    }
}
