package com.tugasakhir.turnamensiamember.View.Team;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alvin on 7/21/2017.
 */

public class TeamCaptainViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageIV;
    private TextView mNameTV;
    private TextView mSteamIdTV;
    private TextView mJoinedDateTV;
    private Long id;

    public TeamCaptainViewHolder(View itemView) {
        super(itemView);

        mImageIV = (ImageView) itemView.findViewById(R.id.captain_image);
        mNameTV = (TextView) itemView.findViewById(R.id.captain_name);
        mSteamIdTV = (TextView) itemView.findViewById(R.id.captain_steam_id);
        mJoinedDateTV = (TextView) itemView.findViewById(R.id.captain_joined_date);
    }

    public void bindHolder(Member member) {
        Date joinedAt = new Date(Long.parseLong(String.valueOf(member.getJoined_at())) * 1000);

        id = member.getId();
        mNameTV.setText(member.getName());
        mSteamIdTV.setText(member.getSteam32_id());
        mJoinedDateTV.setText("Joined on " + new SimpleDateFormat("dd MMMM yyyy").format(joinedAt));
        Picasso.with(itemView.getContext()).load(member.getImage()).into(mImageIV);
    }
}
