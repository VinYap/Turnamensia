package com.tugasakhir.turnamensiamember.View.Registration;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Register.RegisterTournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationConfirmFragment extends Fragment implements iPresenterResponse {
    private TextView mLeftActionTV;
    private TextView mRightActionTV;
    private ImageView mTeamImageIV;
    private TextView mTeamNameTV;
    private ListView mSelectedLV;
    private Button mActionB;

    private Team team;
    private List<Member> members;
    private Long tournamentId;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private RegisterTournamentPresenter mRegisterTournamentPresenter;

    public RegistrationConfirmFragment() {
        // Required empty public constructor
    }

    public static RegistrationConfirmFragment newInstance(Team team, List<Member> members, Long tournamentId) {
        RegistrationConfirmFragment fragment = new RegistrationConfirmFragment();
        fragment.team = team;
        fragment.members = members;
        fragment.tournamentId = tournamentId;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration_confirm, container, false);

        mLeftActionTV = (TextView) view.findViewById(R.id.left_action);
        mRightActionTV = (TextView) view.findViewById(R.id.right_action);
        mTeamImageIV = (ImageView) view.findViewById(R.id.team_image);
        mTeamNameTV = (TextView) view.findViewById(R.id.team_name);
        mSelectedLV = (ListView) view.findViewById(R.id.selected_player);
        mActionB = (Button) view.findViewById(R.id.next_action);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(getContext());
        mRegisterTournamentPresenter = new RegisterTournamentPresenter(this);

        mLeftActionTV.setText("Choose Your Player");
        mRightActionTV.setText("Finish");
        mLeftActionTV.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextHint));

        mTeamNameTV.setText(team.getName());
        Picasso.with(getContext()).load(team.getImage()).into(mTeamImageIV);

        RegistrationConfirmAdapter adapter = new RegistrationConfirmAdapter(getContext(), members);
        mSelectedLV.setAdapter(adapter);

        mActionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                List<Long> membersId = new ArrayList<Long>();
                for (Member member : members) {
                    membersId.add(member.getId());
                }
                String token = mSessionManager.getTokenLoggedIn();
                mRegisterTournamentPresenter.doParticipantRegisterTournament(token, tournamentId, team.getId(), membersId);
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
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
