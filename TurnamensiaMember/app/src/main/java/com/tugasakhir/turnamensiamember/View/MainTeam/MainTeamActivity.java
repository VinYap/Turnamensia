package com.tugasakhir.turnamensiamember.View.MainTeam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Response.AccountTeamResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.List;

import static com.tugasakhir.turnamensiamember.View.Account.AccountTeamFragment.REQUEST_CODE;

public class MainTeamActivity extends BaseActivity implements iPresenterResponse {
    private RecyclerView mTeamRV;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamPresenter mTeamPresenter;
    private MainTeamAdapter mAdapter;

    private List<Team> teams;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main_team, mBaseLayout);

        showUpCaretMenu();

        mTeamRV = (RecyclerView) findViewById(R.id.main_team_rv);
        mTeamRV.setLayoutManager(new LinearLayoutManager(this));
        mTeamRV.setHasFixedSize(true);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);
        mTeamPresenter = new TeamPresenter(this);

        mAdapter = new MainTeamAdapter(teams);
        mTeamRV.setAdapter(mAdapter);

        this.setTitle("List Team");

        token = mSessionManager.getTokenLoggedIn();

        mProgressDialog.show();
        mTeamPresenter.doGetParticipantTeam(token, null);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        teams = ((AccountTeamResponse) response).getTeams();
        mAdapter.setTeams(teams);
        mAdapter.notifyDataSetChanged();
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
        MenuItem mFilter = menu.findItem(R.id.filter);
        mFilter.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        this.mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProgressDialog.show();
                mTeamPresenter.doGetParticipantTeam(token, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mProgressDialog.show();
            mTeamPresenter.doGetParticipantTeam(token, mSearchView.getQuery().toString());
        }
    }
}
