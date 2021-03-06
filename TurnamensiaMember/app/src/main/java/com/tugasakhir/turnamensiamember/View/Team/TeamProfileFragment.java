package com.tugasakhir.turnamensiamember.View.Team;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Response.PictureResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.tugasakhir.turnamensiamember.View.MainTeam.MainTeamViewHolder.HAS_INVITATION;
import static com.tugasakhir.turnamensiamember.View.MainTeam.MainTeamViewHolder.IS_SEARCH;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamProfileFragment extends Fragment implements iPresenterResponse {
    private ImageView mImageIV;
    private ImageView mChangeImageIV;
    private ImageView mDeleteImageIV;
    private EditText mNameET;
    private EditText mJoinCodeET;
    private Button mUpdateB;
    private TextInputLayout mJoinCodeTIL;
    private Button mJoinB;
    private Button mAcceptB;
    private Button mRejectB;
    private Button mDisbandLeaveB;

    private ProgressDialog mProgressDialog;
    private TeamPresenter mTeamPresenter;
    private TeamPresenter teamPresenter;
    private SessionManager mSessionManager;

    private Integer mStatus;
    private String token;
    private Long id;

    private static final String TEAM_DETAIL = "TEAM_DETAIL";
    private static final String IS_LEADER = "IS_LEADER";
    private static final String TEAM_ID = "TEAM_ID";
    private static final Integer IMAGE_CODE = 1;
    private static final String IN_TEAM = "IN_TEAM";

    public TeamProfileFragment() {
        // Required empty public constructor
    }

    public static TeamProfileFragment newInstance(Team team, Boolean isLeader, Boolean inTeam, boolean isSearch, boolean hasInvitation) {
        Bundle args = new Bundle();
        args.putSerializable(TEAM_DETAIL, team);
        args.putBoolean(IS_LEADER, isLeader);
        args.putBoolean(IN_TEAM, inTeam != null ? inTeam : false);
        args.putBoolean(IS_SEARCH, isSearch);
        args.putBoolean(HAS_INVITATION, hasInvitation);
        args.putLong(TEAM_ID, team.getId());
        TeamProfileFragment fragment = new TeamProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_profile, container, false);

        mImageIV = (ImageView) view.findViewById(R.id.team_profile_image);
        mChangeImageIV = (ImageView) view.findViewById(R.id.team_profile_change_image);
        mDeleteImageIV = (ImageView) view.findViewById(R.id.team_profile_delete_image);
        mNameET = (EditText) view.findViewById(R.id.team_name);
        mJoinCodeET = (EditText) view.findViewById(R.id.team_join_code);
        mUpdateB = (Button) view.findViewById(R.id.update_team);
        mJoinCodeTIL = (TextInputLayout) view.findViewById(R.id.team_join_code_container);
        mJoinB = (Button) view.findViewById(R.id.team_join);
        mAcceptB = (Button) view.findViewById(R.id.team_accept);
        mRejectB = (Button) view.findViewById(R.id.team_reject);
        mDisbandLeaveB = (Button) view.findViewById(R.id.disband_leave);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTeamPresenter = new TeamPresenter(this);
        mSessionManager = new SessionManager(getContext());

        teamPresenter = new TeamPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();
                Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
                getActivity().setResult(RESULT_OK);
                getActivity().finish();
            }

            @Override
            public void doFail(String message) {
                mProgressDialog.dismiss();
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doConnectionError(int message) {
                mProgressDialog.dismiss();
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        token = mSessionManager.getTokenLoggedIn();
        id = getArguments().getLong(TEAM_ID);
        final Team team = (Team) getArguments().getSerializable(TEAM_DETAIL);

        final Boolean isLeader = getArguments().getBoolean(IS_LEADER);
        Boolean inTeam = getArguments().getBoolean(IN_TEAM);
        boolean isSearch = getArguments().getBoolean(IS_SEARCH);
        boolean hasInvitation = getArguments().getBoolean(HAS_INVITATION);

        mChangeImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMAGE_CODE);
            }
        });

        mDeleteImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Remove Picture")
                        .setMessage("Do you want to remove it?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mProgressDialog.show();
                                mStatus = 2;
                                String token = mSessionManager.getTokenLoggedIn();
                                Long id = getArguments().getLong(TEAM_ID);
                                mTeamPresenter.doDeleteParticipantTeamPicture(token, id);
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

        mUpdateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameET.getText().toString();
                String joinCode = mJoinCodeET.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getContext(), "Please fill team name", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.show();
                    mStatus = 1;
                    String token = mSessionManager.getTokenLoggedIn();
                    Long id = getArguments().getLong(TEAM_ID);
                    mTeamPresenter.doUpdateParticipantTeamProfile(token, id, name, joinCode);
                }
            }
        });

        mDisbandLeaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }

                if (isLeader == true) {
                    builder.setTitle("Disband Team")
                            .setMessage("Do you really want to disband team?");
                }
                else {
                    builder.setTitle("Leave Team")
                            .setMessage("Do you really want to leave team?");
                }
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (isLeader == true) {
                                    teamPresenter.doDisbandParticipantTeam(token, id);
                                }
                                else {
                                    teamPresenter.doLeaveParticipantTeam(token, id);
                                }
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

        mJoinB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(team.getJoin_code())) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
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
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle("Join Code");

                    final EditText input = new EditText(getContext());
                    builder.setView(input);

                    builder.setPositiveButton("Join", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String joinCode = input.getText().toString();
                            if (TextUtils.isEmpty(joinCode)) {
                                Toast.makeText(getContext(), "Please fill Join Code", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mProgressDialog.show();
                                teamPresenter.doJoinPartcipantTeam(token, id, joinCode);
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

        mAcceptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
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

        mRejectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Accept Invitation")
                        .setMessage("Do you want to accept this invitation?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mProgressDialog.show();
                                teamPresenter.doRejectPartcipantTeam(token, id);
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

        initializedData(team);

        if (isLeader == false) {
            mUpdateB.setVisibility(View.GONE);
            mChangeImageIV.setVisibility(View.INVISIBLE);
            mDeleteImageIV.setVisibility(View.INVISIBLE);
            mNameET.setEnabled(false);
            mJoinCodeET.setEnabled(false);
            mDisbandLeaveB.setText("Leave Team");
        }
        if (isSearch == true && inTeam == false) {
            mJoinCodeTIL.setVisibility(View.GONE);
            mDisbandLeaveB.setVisibility(View.GONE);
            if (hasInvitation) {
                mRejectB.setVisibility(View.VISIBLE);
                mAcceptB.setVisibility(View.VISIBLE);
            }
            else {
                mJoinB.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(team.getJoin_code())) {
                    mJoinB.setBackground(ContextCompat.getDrawable(getContext(), R.color.colorButtonPending));
                }
            }
        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            mProgressDialog.show();
            mStatus = 2;
            Uri selectedImage = data.getData();
            File file = new File(getRealPathFromURI(getContext(), selectedImage));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part image = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
            mTeamPresenter.doUpdateParticipantTeamPicture(token, id, image);
        }
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        if (mStatus == 2) Picasso.with(getContext()).load(((PictureResponse) response).getFile_path()).into(mImageIV);
        getActivity().setResult(RESULT_OK);
    }

    @Override
    public void doFail(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doConnectionError(int message) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void initializedData(Team team) {
        mNameET.setText(team.getName());
        mJoinCodeET.setText(team.getJoin_code());
        Picasso.with(getContext()).load(team.getImage()).into(mImageIV);
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
