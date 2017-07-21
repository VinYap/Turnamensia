package com.tugasakhir.turnamensiamember.View.Team;


import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

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

    private SessionManager mSessionManager;

    private static final String TEAM_DETAIL = "TEAM_DETAIL";
    private static final String IS_LEADER = "IS_LEADER";

    public TeamProfileFragment() {
        // Required empty public constructor
    }

    public static TeamProfileFragment newInstance(Team team, Boolean isLeader) {
        Bundle args = new Bundle();
        args.putSerializable(TEAM_DETAIL, team);
        args.putBoolean(IS_LEADER, isLeader);
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

        mSessionManager = new SessionManager(getContext());

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
                }
            }
        });

        initializedData((Team) getArguments().getSerializable(TEAM_DETAIL));

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
    }

    @Override
    public void doFail(String message) {
        mProgressDialog.dismiss();
    }

    @Override
    public void doConnectionError(int message) {
        mProgressDialog.dismiss();
    }

    private void initializedData(Team team) {
        mNameET.setText(team.getName());
        mJoinCodeET.setText(team.getJoin_code());
        Picasso.with(getContext()).load(team.getImage()).into(mImageIV);
    }
}
