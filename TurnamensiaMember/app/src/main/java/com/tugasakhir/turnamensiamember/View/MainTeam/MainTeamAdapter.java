package com.tugasakhir.turnamensiamember.View.MainTeam;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

    public static final int TYPE_TEAM = 0;
    public static final int TYPE_INVITED = 1;
    public static final int TYPE_NOT_INVITED = 2;
    public static final int TYPE_NON_TEAM = 3;

    public MainTeamAdapter(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public MainTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_team, parent, false);
        switch (viewType) {
            case TYPE_TEAM :
                return new MainTeamViewHolder(view, this, true, true);
            case TYPE_INVITED :
                return new MainTeamViewHolder(view, this, false, true);
            case TYPE_NOT_INVITED :
                return new MainTeamViewHolder(view, this, true, false);
            case TYPE_NON_TEAM :
                return new MainTeamViewHolder(view, this, false, false);
            default :
                return null;
        }
    }

    @Override
    public void onBindViewHolder(MainTeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.bindHolder(team, position);
    }

    @Override
    public int getItemCount() {
        if (teams != null) return teams.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (teams != null) {
            if (teams.get(position).getIn_team() == true) return TYPE_TEAM;
            if (teams.get(position).getHas_invitation() == true) return TYPE_INVITED;
            if (!TextUtils.isEmpty(teams.get(position).getJoin_code())) return TYPE_NON_TEAM;
            return TYPE_NOT_INVITED;
        }
        return TYPE_TEAM;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void updateAt(int position, Integer count) {
        if (count != null) {
            this.teams.get(position).setNumber_of_members(count);
            this.teams.get(position).setIn_team(true);
        }
        this.teams.get(position).setHas_invitation(false);
        notifyItemChanged(position);
    }
}
