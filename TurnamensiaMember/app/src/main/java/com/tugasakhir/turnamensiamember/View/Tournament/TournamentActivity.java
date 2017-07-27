package com.tugasakhir.turnamensiamember.View.Tournament;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import com.tugasakhir.turnamensiamember.View.Registration.RegistrationActivity;
import com.tugasakhir.turnamensiamember.View.Schedule.ScheduleActivity;

import static com.tugasakhir.turnamensiamember.View.Main.MainViewHolder.IS_OPEN;
import static com.tugasakhir.turnamensiamember.View.Main.MainViewHolder.TOURNAMENT_KEY;

public class TournamentActivity extends BaseActivity implements iPresenterResponse {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mScheduleFAB;
    private ImageView mImageIV;
    private TextView mNameTV;
    private Button mRegisterIV;

    private TournamentPresenter mTournamentPresenter;
    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tournament, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.tournament_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.tournament_pager);
        mScheduleFAB = (FloatingActionButton) findViewById(R.id.schedule_fab);
        mImageIV = (ImageView) findViewById(R.id.tournament_image);
        mNameTV = (TextView) findViewById(R.id.league_name);
        mRegisterIV = (Button) findViewById(R.id.register_tournament);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentPresenter = new TournamentPresenter(this);
        mSessionManager = new SessionManager(this);

        mScheduleFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
            }
        });

        mRegisterIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSessionManager.isUserLoggedIn()) startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                else startActivity(new Intent(getApplicationContext(), AuthActivity.class));
            }
        });

        Long tournamentId = getIntent().getLongExtra(TOURNAMENT_KEY, -1);
        Boolean isOpen = getIntent().getBooleanExtra(IS_OPEN, false);

        if (isOpen == false) {
            mRegisterIV.setText("Register close");
            mRegisterIV.setEnabled(false);
        }

        mProgressDialog.show();
        mTournamentPresenter.doGetParticipantTournamentDetail(tournamentId);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();

        TournamentDetailResponse tournamentDetailResponse = (TournamentDetailResponse) response;

        mNameTV.setText(tournamentDetailResponse.getTournament().getName());
        Picasso.with(this).load(tournamentDetailResponse.getTournament().getImage()).into(mImageIV);

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
}
