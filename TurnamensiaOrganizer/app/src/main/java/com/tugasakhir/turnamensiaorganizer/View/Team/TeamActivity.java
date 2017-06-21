package com.tugasakhir.turnamensiaorganizer.View.Team;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Team;
import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends BaseActivity {
    private ListView mTeamLV;
    private List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_team, mBaseLayout);

        showUpCaretMenu();

        mTeamLV = (ListView) findViewById(R.id.team_list_view);
        View header = getLayoutInflater().inflate(R.layout.item_team_header, null);
        mTeamLV.addHeaderView(header);

        initializeData();

        TeamAdapter adapter = new TeamAdapter(this, teams);
        mTeamLV.setAdapter(adapter);
    }

    private void initializeData() {
        teams = new ArrayList<Team>();

        for (int i = 0; i < 9; i++) {
            Team team = new Team();

            team.setUserPhoto(R.drawable.ib);
            team.setUsername("User Name ke-" + i);
            team.setUserProfile(R.drawable.ic_menu_share);
            team.setUserStatus(R.drawable.ic_menu_camera);

            teams.add(team);
        }
    }
}
