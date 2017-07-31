package com.tugasakhir.turnamensiamember.View.Organizer.Match;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.OrganizerTournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.Main.MainOrganizerViewHolder.TOURNAMENT_KEY;
import static com.tugasakhir.turnamensiamember.View.Main.MainOrganizerViewHolder.TOURNAMENT_NAME_KEY;

public class OMatchActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private SessionManager mSessionManager;

    private Long id;
    private ProgressDialog mProgressDialog;
    private TournamentPresenter mTournamentOrganizerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_organizer_match, mBaseLayout);

        showUpCaretMenu();

        mTabLayout = (TabLayout) findViewById(R.id.match_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.match_pager);

        mSessionManager = new SessionManager(getApplicationContext());

        id = getIntent().getExtras().getLong(TOURNAMENT_KEY, -1);
        setTitle(getIntent().getExtras().getString(TOURNAMENT_NAME_KEY, ""));

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentOrganizerPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                OMatchPagerAdapter adapter = new OMatchPagerAdapter(getSupportFragmentManager(), ((OrganizerTournamentDetailResponse) response).getMatches());
                if (((OrganizerTournamentDetailResponse) response).getMatches().size() <= 2) {
                    mTabLayout.setTabMode(TabLayout.MODE_FIXED);
                } else {
                    mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                }
                mViewPager.setAdapter(adapter);
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

        mTabLayout.setupWithViewPager(mViewPager);

        fetchDataMatches();
    }

    public void fetchDataMatches() {
        String token = mSessionManager.getTokenLoggedIn();

        mProgressDialog.show();
        mTournamentOrganizerPresenter.doGetOrganizerTournamentDetail(token, id);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
