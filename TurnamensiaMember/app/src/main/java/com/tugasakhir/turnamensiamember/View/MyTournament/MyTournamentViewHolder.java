package com.tugasakhir.turnamensiamember.View.MyTournament;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournamentViewHolder extends RecyclerView.ViewHolder {
    private ImageView mPhotoIV;
    private TextView mTournamentNameTV;
    private TextView mTeamNameTV;
    private TextView mDateTV;
    private ImageView mQrCodeIV;

    public MyTournamentViewHolder(View itemView, boolean isCompleted) {
        super(itemView);

        mPhotoIV = (ImageView) itemView.findViewById(R.id.tournament_image);
        mTournamentNameTV = (TextView) itemView.findViewById(R.id.tournament_name);
        mTeamNameTV = (TextView) itemView.findViewById(R.id.team_name);
        mDateTV = (TextView) itemView.findViewById(R.id.tournament_date);
        mQrCodeIV = (ImageView) itemView.findViewById(R.id.tournament_qr_code);

        if (isCompleted == true) {
            mQrCodeIV.setVisibility(View.GONE);
        }
    }

    public void bindHolder() {

    }
}
