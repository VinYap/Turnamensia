package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.TournamentRegistration;
import com.tugasakhir.turnamensiamember.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyRegistrationViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameTV;
    private TextView mSizeTV;
    private TextView mDateTV;
    private Button mConfirmB;
    private ImageView mStatusIV;
    private TextView mStatusTV;

    private Long id;

    public static final String REGISTRATION_KEY = "REGISTRATION_KEY";

    public MyRegistrationViewHolder(View view, boolean isAccepted) {
        super(view);

        mNameTV = (TextView) itemView.findViewById(R.id.tournament_name);
        mSizeTV = (TextView) itemView.findViewById(R.id.team_size);
        mDateTV = (TextView) itemView.findViewById(R.id.registration_date);
        mConfirmB = (Button) itemView.findViewById(R.id.confirm);
        mStatusIV = (ImageView) itemView.findViewById(R.id.status_image);
        mStatusTV = (TextView) itemView.findViewById(R.id.status);

        mConfirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                Intent intent = new Intent(context, RegistrationConfirmationActivity.class);
                intent.putExtra(REGISTRATION_KEY, id);
                context.startActivity(intent);
            }
        });

        if (isAccepted == true) mConfirmB.setVisibility(View.INVISIBLE);
    }

    public void bindHolder(TournamentRegistration tournamentRegistration) {
        Date registerAt = new Date(Long.parseLong(String.valueOf(tournamentRegistration.getRegister_at())) * 1000);

        this.id = tournamentRegistration.getId();

        mNameTV.setText(tournamentRegistration.getName());
        mSizeTV.setText(tournamentRegistration.getTeam_size().toString().concat(" Players"));
        mDateTV.setText("Register on " + new SimpleDateFormat("dd MMMM yyyy").format(registerAt));
        mStatusTV.setText(tournamentRegistration.getStatus());
    }
}
