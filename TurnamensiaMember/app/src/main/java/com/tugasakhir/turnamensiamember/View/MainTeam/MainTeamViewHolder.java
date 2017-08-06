package com.tugasakhir.turnamensiamember.View.MainTeam;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Response.CountResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Team.TeamActivity;

import static com.tugasakhir.turnamensiamember.View.Account.AccountTeamViewHolder.TEAM_KEY;

/**
 * Created by Asus on 30/07/2017.
 */

public class MainTeamViewHolder extends RecyclerView.ViewHolder implements iPresenterResponse {
    private ImageView mPhotoIV;
    private TextView mNameTV;
    private TextView mMemberTV;
    private Button mJoinB;
    private Button mAcceptedB;
    private Button mRejectedB;

    private Long id;
    private int position;
    private boolean canJoin;
    private MainTeamAdapter adapter;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamPresenter mTeamPresenter;

    public static final String IS_SEARCH = "IS_SEARCH";
    public static final String HAS_INVITATION = "HAS_INVITATION";

    public MainTeamViewHolder(View view, MainTeamAdapter adapter, boolean inTeam, final boolean hasInvitation) {
        super(view);

        this.adapter = adapter;
        mPhotoIV = (ImageView) itemView.findViewById(R.id.team_image);
        mNameTV = (TextView) itemView.findViewById(R.id.team_name);
        mMemberTV = (TextView) itemView.findViewById(R.id.team_member);
        mJoinB = (Button) itemView.findViewById(R.id.join_team);
        mAcceptedB = (Button) itemView.findViewById(R.id.accept_team);
        mRejectedB = (Button) itemView.findViewById(R.id.reject_team);

        mProgressDialog = new ProgressDialog(itemView.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(itemView.getContext());
        mTeamPresenter = new TeamPresenter(this);

        final String token = mSessionManager.getTokenLoggedIn();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                Intent intent = new Intent(context, TeamActivity.class);
                intent.putExtra(TEAM_KEY, id);
                intent.putExtra(IS_SEARCH, true);
                intent.putExtra(HAS_INVITATION, hasInvitation);
                context.startActivity(intent);
            }
        });

        mJoinB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canJoin) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(itemView.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(itemView.getContext());
                    }
                    builder.setTitle("Join Team")
                            .setMessage("Do you want to join the team?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    mProgressDialog.show();
                                    mTeamPresenter.doJoinPartcipantTeam(token, id, null);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
                else {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(itemView.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(itemView.getContext());
                    }
                    builder.setTitle("Join Code");

                    final EditText input = new EditText(itemView.getContext());
                    builder.setView(input);

                    builder.setPositiveButton("Join", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String joinCode = input.getText().toString();
                            if (TextUtils.isEmpty(joinCode)) {
                                Toast.makeText(itemView.getContext(), "Please fill Join Code", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mProgressDialog.show();
                                mTeamPresenter.doJoinPartcipantTeam(token, id, joinCode);
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }
        });

        mAcceptedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(itemView.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(itemView.getContext());
                }
                builder.setTitle("Accept Invitation")
                        .setMessage("Do you want to accept this invitation?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mProgressDialog.show();
                                mTeamPresenter.doAcceptPartcipantTeam(token, id);
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

        mRejectedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(itemView.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(itemView.getContext());
                }
                builder.setTitle("Reject Invitation")
                        .setMessage("Do you want to reject this invitation?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mProgressDialog.show();
                                mTeamPresenter.doRejectPartcipantTeam(token, id);
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

        if (inTeam) {
            mAcceptedB.setVisibility(View.GONE);
            mRejectedB.setVisibility(View.GONE);
            if (hasInvitation) mJoinB.setVisibility(View.GONE);
            else mJoinB.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.color.colorButtonPending));
        }
        else {
            if (hasInvitation) {
                mJoinB.setVisibility(View.GONE);
            }
            else {
                mAcceptedB.setVisibility(View.GONE);
                mRejectedB.setVisibility(View.GONE);
            }
        }
    }

    public void bindHolder(Team team, int position) {
        this.id = team.getId();
        this.position = position;
        this.canJoin = TextUtils.isEmpty(team.getJoin_code());

        mNameTV.setText(team.getName());
        mMemberTV.setText(team.getNumber_of_members().toString().concat(" Member"));
        Picasso.with(itemView.getContext()).load(team.getImage()).into(mPhotoIV);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(itemView.getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        adapter.updateAt(position, ((CountResponse) response).getCount());
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
