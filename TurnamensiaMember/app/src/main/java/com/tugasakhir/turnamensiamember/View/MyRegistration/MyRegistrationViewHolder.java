package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
    private String name;

    public static final String REGISTRATION_KEY = "REGISTRATION_KEY";
    public static final String TOURNAMENT_NAME = "TOURNAMENT_NAME";

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
                intent.putExtra(TOURNAMENT_NAME, name);
                context.startActivity(intent);
            }
        });

        if (isAccepted == true) mConfirmB.setVisibility(View.INVISIBLE);
    }

    public void bindHolder(TournamentRegistration tournamentRegistration) {
        Date registerAt = new Date(Long.parseLong(String.valueOf(tournamentRegistration.getRegister_at())) * 1000);
        String status = tournamentRegistration.getStatus();

        this.id = tournamentRegistration.getId();
        this.name = tournamentRegistration.getName();

        mNameTV.setText(tournamentRegistration.getName());
        mSizeTV.setText(tournamentRegistration.getTeam_size().toString().concat(" Players"));
        mDateTV.setText("Register on " + new SimpleDateFormat("dd MMMM yyyy").format(registerAt));
        mStatusTV.setText(tournamentRegistration.getStatus());

        if (status.equals("Accepted")) {
            mStatusTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            mStatusTV.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorButtonApprove));
            mStatusIV.setImageResource(R.drawable.ic_check_green_32dp);
        }
        else if (status.equals("Rejected")) {
            mStatusTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            mStatusTV.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorButtonRejected));
            mStatusIV.setImageResource(R.drawable.ic_block_red_500_24dp);
        }
        else if (status.equals("Pending")) {
            mStatusTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            mStatusTV.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorButtonPending));
            mStatusIV.setImageResource(R.drawable.ic_access_time_blue_32dp);
        }
        else if (status.equals("Not Confirmed")) {
            mStatusTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            mStatusTV.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorOrange));
            mStatusIV.setImageResource(R.drawable.ic_error_24dp);
        }
    }
}
