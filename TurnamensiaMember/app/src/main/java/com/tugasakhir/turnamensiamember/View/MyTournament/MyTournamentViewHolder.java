package com.tugasakhir.turnamensiamember.View.MyTournament;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.MyTournament;
import com.tugasakhir.turnamensiamember.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournamentViewHolder extends RecyclerView.ViewHolder {
    private ImageView mPhotoIV;
    private TextView mTournamentNameTV;
    private TextView mTeamNameTV;
    private TextView mDateTV;
    private ImageView mQrCodeIV;

    private String uri;

    public MyTournamentViewHolder(View view, boolean isCompleted) {
        super(view);

        mPhotoIV = (ImageView) itemView.findViewById(R.id.tournament_image);
        mTournamentNameTV = (TextView) itemView.findViewById(R.id.tournament_name);
        mTeamNameTV = (TextView) itemView.findViewById(R.id.team_name);
        mDateTV = (TextView) itemView.findViewById(R.id.tournament_date);
        mQrCodeIV = (ImageView) itemView.findViewById(R.id.tournament_qr_code);

        mQrCodeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                ((MyTournamentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_my_tournament, MyQrCodeFragment.newInstance(uri))
                        .addToBackStack(null)
                        .commit();
            }
        });

        if (isCompleted == true) {
            mQrCodeIV.setVisibility(View.GONE);
        }
    }

    public void bindHolder(MyTournament myTournament) {
        Date startDate = new Date(Long.parseLong(String.valueOf(myTournament.getStart_date())) * 1000);
        Date endDate = new Date(Long.parseLong(String.valueOf(myTournament.getEnd_date())) * 1000);

        this.uri = myTournament.getQr_identifier();

        mTournamentNameTV.setText(myTournament.getTournament_name());
        mTeamNameTV.setText(myTournament.getTeam_name());
        mDateTV.setText(new SimpleDateFormat("dd MMMM yyyy").format(startDate) + " - " + new SimpleDateFormat("dd MMMM yyyy").format(endDate));
        Picasso.with(itemView.getContext()).load(myTournament.getImage()).into(mPhotoIV);
        Picasso.with(itemView.getContext()).load(this.uri).into(mQrCodeIV);
    }
}
