package com.tugasakhir.turnamensiamember.View.Team;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamDetailPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

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

    private ProgressDialog mProgressDialog;
    private TeamDetailPresenter mTeamDetailPresenter;
    private SessionManager mSessionManager;

    private Integer mStatus;

    private static final String TEAM_DETAIL = "TEAM_DETAIL";
    private static final String IS_LEADER = "IS_LEADER";
    private static final String TEAM_ID = "TEAM_ID";
    private static final Integer IMAGE_CODE = 1;

    public TeamProfileFragment() {
        // Required empty public constructor
    }

    public static TeamProfileFragment newInstance(Team team, Boolean isLeader) {
        Bundle args = new Bundle();
        args.putSerializable(TEAM_DETAIL, team);
        args.putBoolean(IS_LEADER, isLeader);
        args.putLong(TEAM_ID, team.getId());
        TeamProfileFragment fragment = new TeamProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_profile, container, false);

        mImageIV = (ImageView) view.findViewById(R.id.team_profile_image);
        mChangeImageIV = (ImageView) view.findViewById(R.id.team_profile_change_image);
        mDeleteImageIV = (ImageView) view.findViewById(R.id.team_profile_delete_image);
        mNameET = (EditText) view.findViewById(R.id.team_name);
        mJoinCodeET = (EditText) view.findViewById(R.id.team_join_code);
        mUpdateB = (Button) view.findViewById(R.id.update_team);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTeamDetailPresenter = new TeamDetailPresenter(this);
        mSessionManager = new SessionManager(getContext());

        mChangeImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMAGE_CODE);
            }
        });

        mDeleteImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                mStatus = 2;
                String token = mSessionManager.getTokenLoggedIn();
                Long id = getArguments().getLong(TEAM_ID);
                mTeamDetailPresenter.doDeleteParticipantTeamPicture(token, id);
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
                    mTeamDetailPresenter.doUpdateParticipantTeamProfile(token, id, name, joinCode);
                }
            }
        });

        initializedData((Team) getArguments().getSerializable(TEAM_DETAIL));

        if (getArguments().getBoolean(IS_LEADER) == false) {
            mUpdateB.setVisibility(View.GONE);
            mChangeImageIV.setVisibility(View.GONE);
            mDeleteImageIV.setVisibility(View.GONE);
            mNameET.setEnabled(false);
            mJoinCodeET.setEnabled(false);
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
            String token = mSessionManager.getTokenLoggedIn();
            Long id = getArguments().getLong(TEAM_ID);
            mTeamDetailPresenter.doUpdateParticipantTeamPicture(token, id, image);
        }
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        if (mStatus == 2) Picasso.with(getContext()).load(((PictureResponse) response).getFile_path()).into(mImageIV);
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
