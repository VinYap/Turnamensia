package com.tugasakhir.turnamensiamember.View.Tournament;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Authentication.AuthActivity;
import com.tugasakhir.turnamensiamember.View.BaseActivity;
import com.tugasakhir.turnamensiamember.View.Organizer.Match.OMatchActivity;
import com.tugasakhir.turnamensiamember.View.Registration.RegistrationActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tugasakhir.turnamensiamember.View.Authentication.LoginFragment.MEMBER_TYPE;
import static com.tugasakhir.turnamensiamember.View.Main.MainOrganizerViewHolder.TOURNAMENT_KEY;
import static com.tugasakhir.turnamensiamember.View.Main.MainOrganizerViewHolder.TOURNAMENT_NAME_KEY;
import static com.tugasakhir.turnamensiamember.View.Main.MainViewHolder.TOURNAMENT_NAME;

public class TournamentActivity extends BaseActivity implements iPresenterResponse {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mScheduleFAB;
    private ImageView mImageIV;
    private TextView mNameTV;
    private TextView mOrganizerTV;
    private TextView mDateTV;
    private Button mRegisterB;
    private ConstraintLayout mBracketCL;

    private TournamentPresenter mTournamentPresenter;
    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;

    private Long tournamentId;
    private String tournamentName;

    private static final Integer REQUEST_CODE = 1;
    public static final String IS_CLICKABLE = "IS_CLICKABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tournament, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.tournament_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.tournament_pager);
        mScheduleFAB = (FloatingActionButton) findViewById(R.id.schedule_fab);
        mImageIV = (ImageView) findViewById(R.id.tournament_image);
        mNameTV = (TextView) findViewById(R.id.tournament_name);
        mOrganizerTV = (TextView) findViewById(R.id.team_name);
        mDateTV = (TextView) findViewById(R.id.tournament_date);
        mRegisterB = (Button) findViewById(R.id.register_tournament);
        mBracketCL = (ConstraintLayout) findViewById(R.id.tournament_card);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentPresenter = new TournamentPresenter(this);
        mSessionManager = new SessionManager(this);

        tournamentId = getIntent().getLongExtra(TOURNAMENT_KEY, -1);
        tournamentName = getIntent().getStringExtra(TOURNAMENT_NAME);

        mScheduleFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TournamentActivity.this, OMatchActivity.class);
                intent.putExtra(TOURNAMENT_KEY, tournamentId);
                intent.putExtra(TOURNAMENT_NAME_KEY, "Schedule");
                intent.putExtra(IS_CLICKABLE, false);
                startActivity(intent);
            }
        });

        mRegisterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSessionManager.isUserLoggedIn()) {
                    Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                    intent.putExtra(TOURNAMENT_KEY, tournamentId);
                    intent.putExtra(TOURNAMENT_NAME, tournamentName);
                    startActivity(intent);
                }
                else {
                    startActivityForResult(new Intent(getApplicationContext(), AuthActivity.class), REQUEST_CODE);
                }
            }
        });

        mBracketCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TournamentBracketActivity.class);
                intent.putExtra(TOURNAMENT_KEY, tournamentId);
                intent.putExtra(TOURNAMENT_NAME, tournamentName);
                startActivity(intent);
            }
        });

        this.setTitle(tournamentName);

        mProgressDialog.show();
        mTournamentPresenter.doGetParticipantTournamentDetail(tournamentId);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();

        TournamentDetailResponse tournamentDetailResponse = (TournamentDetailResponse) response;

        Date startDate = new Date(Long.parseLong(String.valueOf(tournamentDetailResponse.getTournament().getStart_date())) * 1000);
        Date endDate = new Date(Long.parseLong(String.valueOf(tournamentDetailResponse.getTournament().getEnd_date())) * 1000);

        mNameTV.setText(tournamentDetailResponse.getTournament().getName());
        mOrganizerTV.setText("Organized By : " + tournamentDetailResponse.getTournament().getOwner());
        mDateTV.setText("Event Date : " + new SimpleDateFormat("dd MMMM").format(startDate) + " - " + new SimpleDateFormat("dd MMMM").format(endDate));
        Picasso.with(this).load(tournamentDetailResponse.getTournament().getImage()).into(mImageIV);

        if (tournamentDetailResponse.getTournament().getRegistration_status() == false) {
            mRegisterB.setText("Registration Closed");
            mRegisterB.setEnabled(false);
            mRegisterB.setBackground(ContextCompat.getDrawable(this, R.color.colorBackground));
            mRegisterB.setTextColor(ContextCompat.getColor(this, R.color.colorOrange));
        }
        else if (mSessionManager.isUserLoggedIn() == false) {
            mRegisterB.setText("Sign In to Register");
        }

        TournamentPagerAdapter adapter = new TournamentPagerAdapter(getSupportFragmentManager(), tournamentDetailResponse);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void doFail(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doConnectionError(int message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getIntExtra(MEMBER_TYPE, -1) == 1) {
                mRegisterB.setText("Register");
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                intent.putExtra(TOURNAMENT_KEY, tournamentId);
                intent.putExtra(TOURNAMENT_NAME, tournamentName);
                startActivity(intent);
            }
            else {
                finish();
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
