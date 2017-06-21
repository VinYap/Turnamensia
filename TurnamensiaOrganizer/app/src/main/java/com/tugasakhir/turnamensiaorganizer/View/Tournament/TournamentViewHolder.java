package com.tugasakhir.turnamensiaorganizer.View.Tournament;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Tournament;
import com.tugasakhir.turnamensiaorganizer.R;
import com.tugasakhir.turnamensiaorganizer.View.Match.MatchActivity;
import com.tugasakhir.turnamensiaorganizer.View.Team.TeamActivity;

/**
 * Created by Asus on 18/06/2017.
 */

public class TournamentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CardView mTournamentCV;
    private TextView mNameTV;
    private TextView mDateTV;
    private TextView mRegistrationTV;
    private TextView mStatusTV;
    private ImageView mPhotoIV;
    private int id;

    public static final String TOURNAMENT_KEY = "TOURNAMENT";

    public TournamentViewHolder(View itemView) {
        super(itemView);

        mTournamentCV = (CardView) itemView.findViewById(R.id.tournament_cardview);
        mNameTV = (TextView) itemView.findViewById(R.id.tournament_name);
        mDateTV = (TextView) itemView.findViewById(R.id.tournament_date);
        mRegistrationTV = (TextView) itemView.findViewById(R.id.tournament_registration);
        mStatusTV = (TextView) itemView.findViewById(R.id.tournament_status);
        mPhotoIV = (ImageView) itemView.findViewById(R.id.tournament_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, MatchActivity.class);
        intent.putExtra(TOURNAMENT_KEY, id);
        context.startActivity(intent);
    }

    public void bindHolder(Tournament tournament, int position) {
        id = position;

        mNameTV.setText(tournament.getName());
        mDateTV.setText(tournament.getDate());
        mRegistrationTV.setText(tournament.getRegistration());
        mStatusTV.setText(tournament.getStatus());
        mPhotoIV.setImageResource(tournament.getPhotoId());
    }
}