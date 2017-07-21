package com.tugasakhir.turnamensiamember.View.Team;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.TeamResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamDetailPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Account.AccountTeamViewHolder;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class TeamActivity extends BaseActivity implements iPresenterResponse {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamDetailPresenter mTeamDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_team, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.team_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.team_pager);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);
        mTeamDetailPresenter = new TeamDetailPresenter(this);

        Long teamId = getIntent().getLongExtra(AccountTeamViewHolder.TEAM_KEY, -1);
        String token = mSessionManager.getTokenLoggedIn();

        mProgressDialog.show();
        mTeamDetailPresenter.doGetParticipantTeamDetail(token, teamId);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        TeamPagerAdapter adapter = new TeamPagerAdapter(getSupportFragmentManager(), (TeamResponse) response);
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
