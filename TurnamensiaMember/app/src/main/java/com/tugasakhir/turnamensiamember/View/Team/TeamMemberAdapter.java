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

public class TeamMemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CAPTAIN = 0;
    private static final int TYPE_MEMBER = 1;

    private List<Member> mMembers;
    private Long mTeamId;
    private Boolean mIsLeader;

    public TeamMemberAdapter(List<Member> members, Long teamId, Boolean isLeader) {
        this.mMembers = members;
        this.mTeamId = teamId;
        this.mIsLeader = isLeader;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_CAPTAIN :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_captain, parent, false);
                return new TeamCaptainViewHolder(view);
            case TYPE_MEMBER :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_member, parent, false);
                return new TeamMemberViewHolder(view, this, mTeamId, mIsLeader);
            default :
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Member member = mMembers.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_CAPTAIN :
                ((TeamCaptainViewHolder) holder).bindHolder(member);
                break;
            case TYPE_MEMBER :
                ((TeamMemberViewHolder) holder).bindHolder(member, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_CAPTAIN;
        return TYPE_MEMBER;
    }

    public void removeAt(int position) {
        mMembers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mMembers.size());
    }
}
