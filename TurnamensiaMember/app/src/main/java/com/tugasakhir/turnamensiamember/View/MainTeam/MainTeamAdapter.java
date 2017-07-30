package com.tugasakhir.turnamensiamember.View.MainTeam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 30/07/2017.
 */

public class MainTeamAdapter extends RecyclerView.Adapter<MainTeamViewHolder> {
    private List<Team> teams;

    public MainTeamAdapter(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public MainTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_team, parent, false);
        return new MainTeamViewHolder(view, false);
    }

    @Override
    public void onBindViewHolder(MainTeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.bindHolder(team);
    }

    @Override
    public int getItemCount() {
        if (teams != null) return teams.size();
        return 0;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
