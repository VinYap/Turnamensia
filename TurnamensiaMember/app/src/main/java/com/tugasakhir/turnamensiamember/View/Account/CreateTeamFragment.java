package com.tugasakhir.turnamensiamember.View.Account;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTeamFragment extends Fragment implements iPresenterResponse {
    private EditText mNameET;
    private EditText mJoinCodeET;
    private Button mCreateB;

    private SessionManager mSessionManager;
    private ProgressDialog mProgressDialog;
    private TeamPresenter mTeamPresenter;

    public CreateTeamFragment() {
        // Required empty public constructor
    }

    public static CreateTeamFragment newInstance() {
        return new CreateTeamFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_team, container, false);

        mNameET = (EditText) view.findViewById(R.id.team_name);
        mJoinCodeET = (EditText) view.findViewById(R.id.team_join_code);
        mCreateB = (Button) view.findViewById(R.id.create_team);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(getContext());
        mTeamPresenter = new TeamPresenter(this);

        mCreateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameET.getText().toString();
                String joinCode = mJoinCodeET.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getContext(), "Please fill team name", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.show();
                    String token = mSessionManager.getTokenLoggedIn();
                    mTeamPresenter.doCreateParticipantTeam(token, name, joinCode);
                }
            }
        });

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();
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
}
