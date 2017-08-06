package com.tugasakhir.turnamensiamember.View.Team;

import android.app.ProgressDialog;
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

/**
 * Created by Asus on 04/08/2017.
 */

public class InviteMemberViewHolder extends RecyclerView.ViewHolder implements iPresenterResponse {
    private ImageView mPhotoIV;
    private TextView mNameTV;
    private TextView mSteamIdTV;
    private ImageView mInviteIV;

    private Long id;
    private int position;

    private InviteMemberAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamPresenter mTeamPresenter;

    public InviteMemberViewHolder(View itemView, InviteMemberAdapter adapter, final Long teamId) {
        super(itemView);

        this.mAdapter = adapter;

        mPhotoIV = (ImageView) itemView.findViewById(R.id.member_image);
        mNameTV = (TextView) itemView.findViewById(R.id.member_name);
        mSteamIdTV = (TextView) itemView.findViewById(R.id.member_steam_id);
        mInviteIV = (ImageView) itemView.findViewById(R.id.invite_member);

        mProgressDialog = new ProgressDialog(itemView.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(itemView.getContext());
        mTeamPresenter = new TeamPresenter(this);

        mInviteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                String token = mSessionManager.getTokenLoggedIn();
                mTeamPresenter.doInviteParticipantTeamMember(token, teamId, id);
            }
        });
    }

    public void bindHolder(Member member, int position) {
        this.position = position;

        id = member.getId();
        mNameTV.setText(member.getName());
        mSteamIdTV.setText(member.getSteam32_id());
        Picasso.with(itemView.getContext()).load(member.getPicture_file_name()).into(mPhotoIV);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(itemView.getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
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
