package com.tugasakhir.turnamensiamember.View.Account;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 02/07/2017.
 */

public class AccountTeamAdapter extends RecyclerView.Adapter<AccountTeamViewHolder> {
    private List<Team> mTeams;

    public AccountTeamAdapter(List<Team> teams) {
        this.mTeams = teams;
    }

    @Override
    public AccountTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_team, parent, false);
        return new AccountTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountTeamViewHolder holder, int position) {
        Team team = mTeams.get(position);
        holder.bindHolder(team);
    }

    @Override
    public int getItemCount() {
        if (mTeams != null) return mTeams.size();
        return 0;
    }

    public void setTeams(List<Team> teams) {
        this.mTeams = teams;
    }
}
