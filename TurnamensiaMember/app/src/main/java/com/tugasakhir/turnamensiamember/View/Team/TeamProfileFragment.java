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

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
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


    public TeamProfileFragment() {
        // Required empty public constructor
    }

    public static TeamProfileFragment newInstance() {
        return new TeamProfileFragment();
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
}
