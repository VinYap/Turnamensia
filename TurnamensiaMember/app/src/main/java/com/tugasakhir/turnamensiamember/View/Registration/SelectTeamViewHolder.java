package com.tugasakhir.turnamensiamember.View.Registration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 03/07/2017.
 */

public class SelectTeamViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameTV;
    private TextView mMemberTV;
    private ImageView mImageIV;

    private Team team;

    public SelectTeamViewHolder(View view, final Tournament tournament) {
        super(view);

        mNameTV = (TextView) itemView.findViewById(R.id.team_name);
        mMemberTV = (TextView) itemView.findViewById(R.id.team_member);
        mImageIV = (ImageView) itemView.findViewById(R.id.team_image);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                ((RegistrationActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_registration, SelectPlayerFragment.newInstance(team, tournament))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void bindHolder(Team team) {
        this.team = team;
        mNameTV.setText(team.getName());
        mMemberTV.setText(team.getNumber_of_valid_members().toString().concat(" member").concat(team.getNumber_of_valid_members() > 1 ? "s" : ""));
        Picasso.with(itemView.getContext()).load(team.getImage()).into(mImageIV);
    }
}
