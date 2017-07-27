package com.tugasakhir.turnamensiamember.View.Registration;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Response.AccountTeamResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountTeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTeamFragment extends Fragment implements iPresenterResponse {
    private RecyclerView mTeamRV;
    private TextView mLeftActionTV;
    private TextView mRightActionTV;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private AccountTeamPresenter mAccountTeamPresenter;

    private List<Team> mTeams;
    private SelectTeamAdapter mAdapter;

    public SelectTeamFragment() {
        // Required empty public constructor
    }

    public static SelectTeamFragment newInstance() {
        return new SelectTeamFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_team, container, false);

        mTeamRV = (RecyclerView) view.findViewById(R.id.select_team);
        mLeftActionTV = (TextView) view.findViewById(R.id.left_action);
        mRightActionTV = (TextView) view.findViewById(R.id.right_action);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(getContext());
        mAccountTeamPresenter = new AccountTeamPresenter(this);

        mLeftActionTV.setText("Select Your Team");
        mRightActionTV.setText("Choose Your Player");

        mTeamRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTeamRV.setHasFixedSize(true);

        String token = mSessionManager.getTokenLoggedIn();
        mProgressDialog.show();
        mAccountTeamPresenter.doGetParticipantAccountTeam(token);

        mAdapter = new SelectTeamAdapter(mTeams);
        mTeamRV.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        mTeams = ((AccountTeamResponse) response).getTeams();
        mAdapter.setTeams(mTeams);
        mAdapter.notifyDataSetChanged();
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
