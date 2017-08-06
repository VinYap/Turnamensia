package com.tugasakhir.turnamensiamember.View.Organizer.Team;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Basic.TournamentRegistration;
import com.tugasakhir.turnamensiamember.Model.Response.MatchTeamAttendanceResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchViewHolder.MATCH_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchViewHolder.MATCH_TEAM_ATTENDANCE_TITLE_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchViewHolder.TOURNAMENT_REGISTRATION_KEY;

public class OTeamActivity extends BaseActivity {
    private ListView mTeamLV;
    private ImageView mTeamIV;
    private TextView mTeamNameTV;
    private TextView mTournamentNameTV;
    private TextView mTournamentRegistrationDateTV;

    private ProgressDialog mProgressDialog;

    private SessionManager mSessionManager;
    private String token;
    private Long match_id;
    private Long tournament_registration_id;
    private Tournament tournament;
    private TournamentRegistration tournament_registration;
    private Team team;
    private List<Member> members;

    private TournamentPresenter mTournamentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_organizer_team, mBaseLayout);

        showUpCaretMenu();

        setTitle(getIntent().getExtras().getString(MATCH_TEAM_ATTENDANCE_TITLE_KEY, ""));

        mTeamLV = (ListView) findViewById(R.id.team_list_view);
        mTeamIV = (ImageView) findViewById(R.id.team_image);
        mTeamNameTV = (TextView) findViewById(R.id.team_name);
        mTournamentNameTV = (TextView) findViewById(R.id.team_league_name);
        mTournamentRegistrationDateTV = (TextView) findViewById(R.id.team_registration);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                tournament = ((MatchTeamAttendanceResponse) response).getTournament();
                tournament_registration = ((MatchTeamAttendanceResponse) response).getTournament_registration();
                team = ((MatchTeamAttendanceResponse) response).getTeam();
                members = ((MatchTeamAttendanceResponse) response).getMembers();

                OTeamAdapter adapter = new OTeamAdapter(getApplicationContext(), members);
                mTeamLV.setAdapter(adapter);

                Picasso.with(getApplicationContext()).load(team.getImage()).into(mTeamIV);
                mTeamNameTV.setText(team.getName());
                mTournamentNameTV.setText(tournament.getName());
                mTournamentRegistrationDateTV.setText(new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(new Date(tournament_registration.getRegister_at() * 1000)));
            }

            @Override
            public void doFail(String message) {
                mProgressDialog.dismiss();
            }

            @Override
            public void doConnectionError(int message) {
                mProgressDialog.dismiss();
            }
        });

        mSessionManager = new SessionManager(this);
        token = mSessionManager.getTokenLoggedIn();
        match_id = getIntent().getExtras().getLong(MATCH_KEY);
        tournament_registration_id = getIntent().getExtras().getLong(TOURNAMENT_REGISTRATION_KEY);

        fetchMatchTeamAttendanceData();
    }

    public void fetchMatchTeamAttendanceData() {
        mProgressDialog.show();
        mTournamentPresenter.doGetMatchTeamAttendance(token, match_id, tournament_registration_id);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
