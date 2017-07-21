package com.tugasakhir.turnamensiamember.View.Organizer.Tournament;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchActivity;

/**
 * Created by Asus on 18/06/2017.
 */

public class OTournamentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CardView mTournamentCV;
    private TextView mNameTV;
    private TextView mDateTV;
    private TextView mRegistrationTV;
    private TextView mStatusTV;
    private ImageView mPhotoIV;
    private Long id;

    public static final String TOURNAMENT_KEY = "TOURNAMENT";

    public OTournamentViewHolder(View itemView) {
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
        Intent intent = new Intent(context, OMatchActivity.class);
        intent.putExtra(TOURNAMENT_KEY, id);
        context.startActivity(intent);
    }

    public void bindHolder(Tournament tournament) {

//        mNameTV.setText(tournament.getName());
//        mDateTV.setText(tournament.getDate());
//        mRegistrationTV.setText(tournament.getRegistration());
//        mStatusTV.setText(tournament.getStatus());
//        mPhotoIV.setImageResource(tournament.getPhotoId());
    }
}