package com.tugasakhir.turnamensiamember.View.Team;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 04/08/2017.
 */

public class InviteMemberAdapter extends RecyclerView.Adapter<InviteMemberViewHolder> {
    private List<Member> members;
    private Long teamId;

    public InviteMemberAdapter(List<Member> members, Long teamId) {
        this.members = members;
        this.teamId = teamId;
    }

    @Override
    public InviteMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_invite, parent, false);
        return new InviteMemberViewHolder(view, this, teamId);
    }

    @Override
    public void onBindViewHolder(InviteMemberViewHolder holder, int position) {
        Member member = members.get(position);
        holder.bindHolder(member, position);
    }

    @Override
    public int getItemCount() {
        if (members != null) return members.size();
        return 0;
    }

    public void removeAt(int position) {
        members.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, members.size());
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
