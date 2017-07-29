package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.R;

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

    public MyRegistrationViewHolder(View itemView) {
        super(itemView);

        mNameTV = (TextView) itemView.findViewById(R.id.tournament_name);
        mSizeTV = (TextView) itemView.findViewById(R.id.team_size);
        mDateTV = (TextView) itemView.findViewById(R.id.registration_date);
        mConfirmB = (Button) itemView.findViewById(R.id.confirm);
        mStatusIV = (ImageView) itemView.findViewById(R.id.status_image);
        mStatusTV = (TextView) itemView.findViewById(R.id.status);
    }

    public void bindHolder() {

    }
}
