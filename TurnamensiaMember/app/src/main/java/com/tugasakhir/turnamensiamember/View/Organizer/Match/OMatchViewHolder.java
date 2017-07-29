package com.tugasakhir.turnamensiamember.View.Organizer.Match;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.OrganizerMatch;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity;
import com.tugasakhir.turnamensiamember.View.Organizer.Team.OTeamActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 18/06/2017.
 */

public class OMatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String MATCH_KEY = "OMatchID";
    public static final String TOURNAMENT_REGISTRATION_KEY = "OMatchTournamentRegistrationID";
    public static final String MATCH_TEAM_ATTENDANCE_TITLE_KEY = "OMatchTeamAttendanceTitle";

    private CardView mMatchCV;
    private TextView mMatchRoundTV;
    private TextView mMatchDateTV;
    private TextView mRadiantTV;
    private TextView mDireTV;
    private ImageView mRadiantIV;
    private ImageView mDireIV;
    private ClickableSpan mClickableSpanRadiant;
    private ClickableSpan mClickableSpanDire;

    private Context mContext;

    private Long match_id;
    private Long radiant_tournament_registration_id;
    private Long dire_tournament_registration_id;
    private String team_attendance_title;

    public OMatchViewHolder(View itemView) {
        super(itemView);

        mMatchCV = (CardView) itemView.findViewById(R.id.match_cardview);
        mMatchRoundTV = (TextView) itemView.findViewById(R.id.match_round);
        mMatchDateTV = (TextView) itemView.findViewById(R.id.match_date);
        mRadiantTV = (TextView) itemView.findViewById(R.id.match_radiant);
        mDireTV = (TextView) itemView.findViewById(R.id.match_dire);
        mRadiantIV = (ImageView) itemView.findViewById(R.id.match_radiant_image);
        mDireIV = (ImageView) itemView.findViewById(R.id.match_dire_image);

        mContext = itemView.getContext();

        mRadiantTV.setPaintFlags(mRadiantTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mRadiantTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OTeamActivity.class);
                intent.putExtra(MATCH_KEY, match_id);
                intent.putExtra(TOURNAMENT_REGISTRATION_KEY, radiant_tournament_registration_id);
                intent.putExtra(MATCH_TEAM_ATTENDANCE_TITLE_KEY, team_attendance_title);
                mContext.startActivity(intent);
            }
        });
        mDireTV.setPaintFlags(mDireTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mDireTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OTeamActivity.class);
                intent.putExtra(MATCH_KEY, match_id);
                intent.putExtra(TOURNAMENT_REGISTRATION_KEY, dire_tournament_registration_id);
                intent.putExtra(MATCH_TEAM_ATTENDANCE_TITLE_KEY, team_attendance_title);
                mContext.startActivity(intent);
            }
        });

        itemView.setOnClickListener(this);
    }

    public void bindHolder(int position, OrganizerMatch match) {
        match_id = match.getId();
        radiant_tournament_registration_id = match.getPlayer_1_id();
        dire_tournament_registration_id = match.getPlayer_2_id();
        team_attendance_title = match.getPlayer_1() + " VS " + match.getPlayer_2();

        mMatchRoundTV.setText("Match " + (position + 1));
        mMatchDateTV.setText(new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(new Date(match.getScheduled_date() * 1000)));
        mRadiantTV.setText(match.getPlayer_1());
        mDireTV.setText(match.getPlayer_2());
        Picasso.with(itemView.getContext()).load(match.getPlayer_1_image()).into(mRadiantIV);
        Picasso.with(itemView.getContext()).load(match.getPlayer_2_image()).into(mDireIV);
    }

    @Override
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(v.getContext(), "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(v.getContext(), QRScannerActivity.class);
            intent.putExtra(MATCH_KEY, match_id);
            intent.putExtra(MATCH_TEAM_ATTENDANCE_TITLE_KEY, team_attendance_title);
            v.getContext().startActivity(intent);
        }
    }
}
