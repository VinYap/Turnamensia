package com.tugasakhir.turnamensiamember.View.Team;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 02/07/2017.
 */

public class TeamMemberViewHolder extends RecyclerView.ViewHolder implements iPresenterResponse {
    private ImageView mImageIV;
    private TextView mNameTV;
    private TextView mSteamIdTV;
    private TextView mJoinedDateTV;
    private ImageView mDeleteB;

    private Long id;
    private int position;

    private TeamMemberAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamPresenter mTeamPresenter;

    public TeamMemberViewHolder(View view, TeamMemberAdapter adapter, final Long teamId, Boolean isLeader) {
        super(view);

        this.mAdapter = adapter;

        mImageIV = (ImageView) itemView.findViewById(R.id.member_image);
        mNameTV = (TextView) itemView.findViewById(R.id.member_name);
        mSteamIdTV = (TextView) itemView.findViewById(R.id.member_steam_id);
        mJoinedDateTV = (TextView) itemView.findViewById(R.id.member_joined_date);
        mDeleteB = (ImageView) itemView.findViewById(R.id.member_delete);

        mProgressDialog = new ProgressDialog(itemView.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(itemView.getContext());
        mTeamPresenter = new TeamPresenter(this);

        mDeleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(itemView.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(itemView.getContext());
                }
                builder.setTitle("Kick Member")
                        .setMessage("Do you really want to kick this member from team?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mProgressDialog.show();
                                String token = mSessionManager.getTokenLoggedIn();
                                mTeamPresenter.doDeleteParticipantTeamMember(token, teamId, id);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        if (isLeader == false) {
            mDeleteB.setVisibility(View.GONE);
        }
    }

    public void bindHolder(Member member, int position) {
        Date joinedAt = new Date(Long.parseLong(String.valueOf(member.getJoined_at())) * 1000);

        this.position = position;

        id = member.getId();
        mNameTV.setText(member.getName());
        mSteamIdTV.setText(member.getSteam32_id());
        mJoinedDateTV.setText("Joined on " + new SimpleDateFormat("dd MMMM yyyy").format(joinedAt));
        Picasso.with(itemView.getContext()).load(member.getImage()).into(mImageIV);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(itemView.getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        ((TeamActivity) itemView.getContext()).setResult(Activity.RESULT_OK);
        this.mAdapter.removeAt(position);
    }

    @Override
    public void doFail(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(itemView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doConnectionError(int message) {
        mProgressDialog.dismiss();
        Toast.makeText(itemView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
