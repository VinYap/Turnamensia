package com.tugasakhir.turnamensiamember.View.Registration;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 10/07/2017.
 */

public class SelectPlayerAdapter extends RecyclerView.Adapter<SelectPlayerViewHolder> {
    private List<Member> members;
    private Button actionB;
    private int teamSize;

    public SelectPlayerAdapter(List<Member> members, Button actionB, Integer teamSize) {
        this.members = members;
        this.actionB = actionB;
        this.teamSize = teamSize;
    }

    @Override
    public SelectPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_player, parent, false);
        return new SelectPlayerViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(SelectPlayerViewHolder holder, int position) {
        Member member = members.get(position);
        holder.bindHolder(member, position);
    }

    @Override
    public int getItemCount() {
        if (members != null) return members.size();
        return 0;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void updateAction(Context context) {
        int cnt = teamSize;
        if (members != null) {
            for (Member member : members) {
                if (member.isSelected()) cnt--;
            }
        }
        if (cnt == 0) {
            actionB.setText("Next");
            actionB.setEnabled(true);
            actionB.setBackground(ContextCompat.getDrawable(context, R.drawable.linear_gradient_orange));
            actionB.setTextColor(ContextCompat.getColor(context, R.color.colorTextLight));
        }
        else {
            actionB.setText("Player Left : " + cnt);
            actionB.setEnabled(false);
            actionB.setBackground(ContextCompat.getDrawable(context, R.color.colorBackground));
            actionB.setTextColor(ContextCompat.getColor(context, R.color.colorOrange));
        }
    }
}
