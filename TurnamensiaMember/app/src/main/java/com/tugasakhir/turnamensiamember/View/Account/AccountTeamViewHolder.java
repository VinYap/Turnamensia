package com.tugasakhir.turnamensiamember.View.Account;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 02/07/2017.
 */

public class AccountTeamViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameTV;
    private TextView mMemberTV;
    private ImageView mImageIV;
    private int id;

    public AccountTeamViewHolder(View itemView) {
        super(itemView);
        mNameTV = (TextView) itemView.findViewById(R.id.team_name);
        mMemberTV = (TextView) itemView.findViewById(R.id.team_member);
        mImageIV = (ImageView) itemView.findViewById(R.id.team_image);
    }

    public void bindHolder(Team team, int position) {
        id = position;

    }
}
