package com.tugasakhir.turnamensiamember.View.Team;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 02/07/2017.
 */

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Member> mMembers;

    public TeamMemberAdapter(List<Member> members) {
        this.mMembers = members;
    }

    @Override
    public TeamMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_captain, parent, false);
        }
        else if (viewType == TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_member, parent, false);
        }
        return new TeamMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamMemberViewHolder holder, int position) {
        Member member = mMembers.get(position);

    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        return TYPE_ITEM;
    }
}
