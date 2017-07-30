package com.tugasakhir.turnamensiamember.View.Account;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class AccountTeamFragment extends Fragment implements iPresenterResponse {
    private RecyclerView mTeamRV;
    private Button mCreateTeamB;

    private List<Team> mTeams;

    private SessionManager mSessionManager;
    private ProgressDialog mProgressDialog;
    private AccountTeamPresenter mAccountTeamPresenter;
    private AccountTeamAdapter mAdapter;

    public AccountTeamFragment() {
        // Required empty public constructor
    }

    public static AccountTeamFragment newInstance() {
        return new AccountTeamFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_team, container, false);

        mCreateTeamB = (Button) view.findViewById(R.id.create_team);
        mTeamRV = (RecyclerView) view.findViewById(R.id.account_team);

        mCreateTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCreateTeamB.setVisibility(View.GONE);
                ((AccountActivity)getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.layout_account_team, CreateTeamFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        mTeamRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTeamRV.setHasFixedSize(true);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mAccountTeamPresenter = new AccountTeamPresenter(this);
        mSessionManager = new SessionManager(getContext());

        mProgressDialog.show();
        String token = mSessionManager.getTokenLoggedIn();
        mAccountTeamPresenter.doGetParticipantAccountTeam(token);

        mAdapter = new AccountTeamAdapter(mTeams);
        mTeamRV.setAdapter(mAdapter);

//        getActivity().getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
//                    mProgressDialog.show();
//                    String token = mSessionManager.getTokenLoggedIn();
//                    mCreateTeamB.setVisibility(View.VISIBLE);
//                    mAccountTeamPresenter.doGetParticipantAccountTeam(token);
//                }
//            }
//        });

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
