package com.tugasakhir.turnamensiamember.View.Account;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Team.TeamActivity;

/**
 * Created by Asus on 02/07/2017.
 */

public class AccountTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mNameTV;
    private TextView mMemberTV;
    private ImageView mImageIV;

    private Long id;

    public static final String TEAM_KEY = "TEAM_KEY";

    public AccountTeamViewHolder(View itemView) {
        super(itemView);

        mNameTV = (TextView) itemView.findViewById(R.id.team_name);
        mMemberTV = (TextView) itemView.findViewById(R.id.team_member);
        mImageIV = (ImageView) itemView.findViewById(R.id.team_image);

        itemView.setOnClickListener(this);
    }

    public void bindHolder(Team team) {
        id = team.getId();
        mNameTV.setText(team.getName());
        mMemberTV.setText(team.getNumber_of_members().toString().concat(" member").concat(team.getNumber_of_members() > 1 ? "s" : ""));
        Picasso.with(itemView.getContext()).load(team.getImage()).into(mImageIV);
    }

    @Override
    public void onClick(View v) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, TeamActivity.class);
        intent.putExtra(TEAM_KEY, id);
        context.startActivity(intent);
    }
}
