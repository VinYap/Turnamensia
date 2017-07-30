package com.tugasakhir.turnamensiamember.View.MainTeam;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 30/07/2017.
 */

public class MainTeamViewHolder extends RecyclerView.ViewHolder {
    private ImageView mPhotoIV;
    private TextView mNameTV;
    private TextView mMemberTV;
    private Button mJoinB;

    private Long id;

    public MainTeamViewHolder(View view, boolean isTeam) {
        super(view);

        mPhotoIV = (ImageView) itemView.findViewById(R.id.team_image);
        mNameTV = (TextView) itemView.findViewById(R.id.team_name);
        mMemberTV = (TextView) itemView.findViewById(R.id.team_member);
        mJoinB = (Button) itemView.findViewById(R.id.join_team);

        mJoinB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (isTeam) mJoinB.setVisibility(View.GONE);
    }

    public void bindHolder(Team team) {
        id = team.getId();

        mNameTV.setText(team.getName());
        mMemberTV.setText(team.getNumber_of_members().toString().concat(" Member"));
        Picasso.with(itemView.getContext()).load(team.getImage()).into(mPhotoIV);
    }
}
