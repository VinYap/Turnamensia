package com.tugasakhir.turnamensiamember.View.Registration;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Response.MemberResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectPlayerFragment extends Fragment implements iPresenterResponse {
    private RecyclerView mPlayerRV;
    private Button mActionB;
    private TextView mLeftActionTV;
    private TextView mRightActionTV;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamPresenter mTeamPresenter;

    private Tournament tournament;
    private Team team;
    private List<Member> members;
    private SelectPlayerAdapter selectAdapter;

    public SelectPlayerFragment() {
        // Required empty public constructor
    }

    public static SelectPlayerFragment newInstance(Team team, Tournament tournament) {
        SelectPlayerFragment fragment = new SelectPlayerFragment();
        fragment.team = team;
        fragment.tournament = tournament;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_player, container, false);

        mPlayerRV = (RecyclerView) view.findViewById(R.id.select_player);
        mActionB = (Button) view.findViewById(R.id.next_action);
        mLeftActionTV = (TextView) view.findViewById(R.id.left_action);
        mRightActionTV = (TextView) view.findViewById(R.id.right_action);

        mLeftActionTV.setText("Choose Your Player");
        mRightActionTV.setText("Finish");

        mPlayerRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlayerRV.setHasFixedSize(true);

        selectAdapter = new SelectPlayerAdapter(members, mActionB, tournament.getTeam_size());
        selectAdapter.updateAction();
        mPlayerRV.setAdapter(selectAdapter);

        mActionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                members = new ArrayList<Member>();
                for (Member member : selectAdapter.getMembers()) {
                    if (member.isSelected()) members.add(member);
                }
                ((RegistrationActivity)getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_registration, RegistrationConfirmFragment.newInstance(team, members, tournament.getId()))
                        .addToBackStack(null)
                        .commit();
            }
        });

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(getContext());
        mTeamPresenter = new TeamPresenter(this);

        mProgressDialog.show();
        String token = mSessionManager.getTokenLoggedIn();
        mTeamPresenter.doGetParticipantTeamMember(token, team.getId());

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();

        members = ((MemberResponse) response).getMembers();
        selectAdapter.setMembers(members);
        selectAdapter.notifyDataSetChanged();
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
