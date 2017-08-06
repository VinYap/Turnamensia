package com.tugasakhir.turnamensiamember.View.Organizer.Team;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Basic.TournamentRegistration;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity.MATCH_ID_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity.QR_CODE_IDENTIFIER_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity.QR_RESULT_MEMBER_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity.QR_RESULT_TEAM_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity.QR_RESULT_TITLE_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity.QR_RESULT_TOURNAMENT_KEY;
import static com.tugasakhir.turnamensiamember.View.Organizer.QRScanner.QRScannerActivity.QR_RESULT_TOURNAMENT_REGISTRATION_KEY;

public class OQrCodeResultActivity extends BaseActivity {
    private ImageView mTeamIV;
    private TextView mTeamNameTV;
    private TextView mTournamentNameTV;
    private TextView mTournamentRegistrationDateTV;
    private ImageView mMemberIV;
    private TextView mMemberNameTV;
    private TextView mMemberSteamIDTV;
    private ImageView mMemberIdentificationIV;
    private Button mApproveB;

    private ProgressDialog mProgressDialog;

    private SessionManager mSessionManager;
    private String token;
    private Long match_id;
    private String qr_identifier;
    private Tournament tournament;
    private TournamentRegistration tournament_registration;
    private Team team;
    private Member member;

    private TournamentPresenter mTournamentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_qr_code_result, mBaseLayout);

        setTitle(getIntent().getExtras().getString(QR_RESULT_TITLE_KEY, ""));

        mTeamIV = (ImageView) findViewById(R.id.team_image);
        mTeamNameTV = (TextView) findViewById(R.id.team_name);
        mTournamentNameTV = (TextView) findViewById(R.id.team_league_name);
        mTournamentRegistrationDateTV = (TextView) findViewById(R.id.team_registration);
        mMemberIV = (ImageView) findViewById(R.id.member_image);
        mMemberNameTV = (TextView) findViewById(R.id.member_name);
        mMemberSteamIDTV = (TextView) findViewById(R.id.member_steam_id);
        mMemberIdentificationIV = (ImageView) findViewById(R.id.member_identification_image);
        mApproveB = (Button) findViewById(R.id.approve_button);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(getApplicationContext());
        token = mSessionManager.getTokenLoggedIn();
        match_id = getIntent().getExtras().getLong(MATCH_ID_KEY);
        qr_identifier = getIntent().getExtras().getString(QR_CODE_IDENTIFIER_KEY);
        tournament = Parcels.unwrap(getIntent().getExtras().getParcelable(QR_RESULT_TOURNAMENT_KEY));
        tournament_registration = Parcels.unwrap(getIntent().getExtras().getParcelable(QR_RESULT_TOURNAMENT_REGISTRATION_KEY));
        team = Parcels.unwrap(getIntent().getExtras().getParcelable(QR_RESULT_TEAM_KEY));
        member = Parcels.unwrap(getIntent().getExtras().getParcelable(QR_RESULT_MEMBER_KEY));

        mTournamentPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void doFail(String message) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doConnectionError(int message) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        Picasso.with(getApplicationContext()).load(team.getImage()).into(mTeamIV);
        mTeamNameTV.setText(team.getName());
        mTournamentNameTV.setText(tournament.getName());
        mTournamentRegistrationDateTV.setText(new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(new Date(tournament_registration.getRegister_at() * 1000)));
        Picasso.with(getApplicationContext()).load(member.getImage()).into(mMemberIV);
        mMemberNameTV.setText(member.getName());
        mMemberSteamIDTV.setText(member.getSteam32_id());
        Picasso.with(getApplicationContext()).load(member.getIdentification_image()).into(mMemberIdentificationIV);
        mApproveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder approve_dialog = new AlertDialog.Builder(v.getContext());
                approve_dialog.setMessage("Are you sure? (Approve this participant)")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mProgressDialog.show();
                            mTournamentPresenter.doPostMatchAttendance(token, match_id, qr_identifier);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
            }
        });

        showUpCaretMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
