package com.tugasakhir.turnamensiamember.View.Organizer.Match;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.OrganizerMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.OrganizerTournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.List;
import java.util.Map;

import static com.tugasakhir.turnamensiamember.View.Main.MainOrganizerViewHolder.TOURNAMENT_KEY;
import static com.tugasakhir.turnamensiamember.View.Main.MainOrganizerViewHolder.TOURNAMENT_NAME_KEY;
import static com.tugasakhir.turnamensiamember.View.Tournament.TournamentActivity.IS_CLICKABLE;

public class OMatchActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private SessionManager mSessionManager;

    private Boolean isClickable;
    private Long id;
    private ProgressDialog mProgressDialog;
    private TournamentPresenter mTournamentOrganizerPresenter;

    private TournamentDetailResponse tournamentDetail;

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
        isClickable = getIntent().getExtras().getBoolean(IS_CLICKABLE, true);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentOrganizerPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                if (response instanceof OrganizerTournamentDetailResponse) {
                    setAdapter(((OrganizerTournamentDetailResponse) response).getMatches());
                }
                else if (response instanceof TournamentDetailResponse) {
                    setAdapter(((TournamentDetailResponse) response).getMatches());
                }
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

        if (isClickable) fetchDataMatches();
        else fetchDataTournamentMatches();
    }

    public void fetchDataTournamentMatches() {
        mProgressDialog.show();
        mTournamentOrganizerPresenter.doGetParticipantTournamentDetail(id);
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

    public void setAdapter(Map<String, List<OrganizerMatch>> matches) {
        OMatchPagerAdapter adapter = new OMatchPagerAdapter(getSupportFragmentManager(), matches, isClickable);
        if (matches.size() <= 2) {
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        mViewPager.setAdapter(adapter);
    }
}
