package com.tugasakhir.turnamensiamember.View.Account;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Team.TeamActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountTeamFragment extends Fragment {
    private RecyclerView mTeamRV;
    private Button mCreateTeamB;

    private List<Team> mTeams;

    public AccountTeamFragment() {
        // Required empty public constructor
    }

    public static AccountTeamFragment newInstance() {
        return new AccountTeamFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_team, container, false);

        mCreateTeamB = (Button) view.findViewById(R.id.create_team);
        mTeamRV = (RecyclerView) view.findViewById(R.id.account_team);

        mCreateTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AccountActivity)getActivity()).startActivity(new Intent(getContext(), TeamActivity.class));
            }
        });

        mTeamRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTeamRV.setHasFixedSize(true);

        initializeData();

        AccountTeamAdapter adapter = new AccountTeamAdapter(mTeams);
        mTeamRV.setAdapter(adapter);

        return view;
    }

    private void initializeData() {
        mTeams = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Team team = new Team();
            mTeams.add(team);
        }
    }
}
