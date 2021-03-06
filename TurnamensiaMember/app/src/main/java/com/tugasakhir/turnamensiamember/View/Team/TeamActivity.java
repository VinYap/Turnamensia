package com.tugasakhir.turnamensiamember.View.Team;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.TeamResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.Account.AccountTeamViewHolder.TEAM_KEY;
import static com.tugasakhir.turnamensiamember.View.MainTeam.MainTeamViewHolder.HAS_INVITATION;
import static com.tugasakhir.turnamensiamember.View.MainTeam.MainTeamViewHolder.IS_SEARCH;

public class TeamActivity extends BaseActivity implements iPresenterResponse {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamPresenter mTeamPresenter;

    private boolean isSearch;
    private boolean hasInvitation;
    private String token;
    private Long teamId;

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
        mTeamPresenter = new TeamPresenter(this);

        teamId = getIntent().getLongExtra(TEAM_KEY, -1);
        token = mSessionManager.getTokenLoggedIn();
        isSearch = getIntent().getBooleanExtra(IS_SEARCH, false);
        hasInvitation = getIntent().getBooleanExtra(HAS_INVITATION, false);

        this.setTitle("My Team");
    }

    @Override
    public void onResume() {
        super.onResume();

        mProgressDialog.show();
        if (isSearch) mTeamPresenter.doGetParticipantTeamSearchDetail(token, teamId);
        else mTeamPresenter.doGetParticipantTeamDetail(token, teamId);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        TeamPagerAdapter adapter = new TeamPagerAdapter(getSupportFragmentManager(), (TeamResponse) response, isSearch, hasInvitation);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        this.setTitle(((TeamResponse) response).getTeam().getName());
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
