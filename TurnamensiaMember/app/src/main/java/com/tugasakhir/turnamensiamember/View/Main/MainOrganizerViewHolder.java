package com.tugasakhir.turnamensiamember.View.Main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrianto on 7/22/2017.
 */

public class MainOrganizerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mNameTV;
    private TextView mDateTV;
    private TextView mRegistrationTV;
    private TextView mStatusTV;
    private TextView mPriceTV;
    private ImageView mPhotoIV;

    private Long id;
    private String tournament_name;

    public static final String TOURNAMENT_KEY = "TOURNAMENT";
    public static final String TOURNAMENT_NAME_KEY = "OTournamentName";

    public MainOrganizerViewHolder(View itemView) {
        super(itemView);

        mNameTV = (TextView) itemView.findViewById(R.id.main_tournament_name);
        mDateTV = (TextView) itemView.findViewById(R.id.main_tournament_date);
        mRegistrationTV = (TextView) itemView.findViewById(R.id.main_tournament_registration);
        mStatusTV = (TextView) itemView.findViewById(R.id.main_tournament_status);
        mPriceTV = (TextView) itemView.findViewById(R.id.main_tournament_price);
        mPhotoIV = (ImageView) itemView.findViewById(R.id.main_tournament_image);

        itemView.setOnClickListener(this);
    }

    public void bindHolder(Tournament tournament) {
        Date startDate = new Date(Long.parseLong(String.valueOf(tournament.getStart_date())) * 1000);
        Date endDate = new Date(Long.parseLong(String.valueOf(tournament.getEnd_date())) * 1000);
        Date registrationClosed = new Date(Long.parseLong(String.valueOf(tournament.getRegistration_closed())) * 1000);

        id = tournament.getId();
        tournament_name = tournament.getName();

        mNameTV.setText(tournament.getName());
        mDateTV.setText(new SimpleDateFormat("dd MMMM").format(startDate) + " - " + new SimpleDateFormat("dd MMMM").format(endDate));
        mRegistrationTV.setText("Registration before " + new SimpleDateFormat("dd MMMM").format(registrationClosed));
        mPriceTV.setText("Rp ".concat(String.format("%,d", tournament.getEntry_fee())));
        mStatusTV.setText(tournament.getStatus());
        Picasso.with(itemView.getContext()).load(tournament.getImage()).into(mPhotoIV);
    }

    @Override
    public void onClick(View v) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, OMatchActivity.class);
        intent.putExtra(TOURNAMENT_KEY, id);
        intent.putExtra(TOURNAMENT_NAME_KEY, tournament_name);
        context.startActivity(intent);
    }
}
