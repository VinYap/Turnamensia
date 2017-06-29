package com.tugasakhir.turnamensiamember.View.Main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 26/06/2017.
 */

public class MainViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameTV;
    private TextView mDateTV;
    private TextView mRegistrationTV;
    private TextView mStatusTV;
    private TextView mPriceTV;
    private ImageView mPhotoIV;
    private int id;

    public MainViewHolder(View itemView) {
        super(itemView);

        mNameTV = (TextView) itemView.findViewById(R.id.main_tournament_name);
        mDateTV = (TextView) itemView.findViewById(R.id.main_tournament_date);
        mRegistrationTV = (TextView) itemView.findViewById(R.id.main_tournament_registration);
        mStatusTV = (TextView) itemView.findViewById(R.id.main_tournament_status);
        mPriceTV = (TextView) itemView.findViewById(R.id.main_tournament_price);
        mPhotoIV = (ImageView) itemView.findViewById(R.id.main_tournament_image);
    }

    public void bindHolder(Tournament tournament, int position) {
        id = position;

        mNameTV.setText(tournament.getName());
        mDateTV.setText(tournament.getDate());
        mRegistrationTV.setText(tournament.getRegistration());
        mStatusTV.setText(tournament.getStatus());
        mPriceTV.setText(tournament.getPrice());
        mPhotoIV.setImageResource(tournament.getPhotoId());
    }
}
