package com.tugasakhir.turnamensiamember.View.Main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView mTournamentRV;

    private List<Tournament> mTournaments;

    private ProgressDialog mProgressDialog;
    private TournamentPresenter mTournamentParticipantPresenter;
    private TournamentPresenter mTournamentOrganizerPresenter;
    private MainAdapter mAdapter;
    private MainOrganizerAdapter mOrganizerAdapter;

    private SessionManager mSessionManager;
    private String token;
    private Integer member_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, mBaseLayout);

        showHamburgerMenu();

        mTournamentRV = (RecyclerView) findViewById(R.id.main_tournament);
        mTournamentRV.setLayoutManager(new LinearLayoutManager(this));
        mTournamentRV.setHasFixedSize(true);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentParticipantPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                mTournamentRV.setAdapter(mAdapter);

                mTournaments = ((TournamentResponse) response).getTournaments();
                mAdapter.setTournaments(mTournaments);
                mAdapter.notifyDataSetChanged();
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

        mTournamentOrganizerPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                mTournamentRV.setAdapter(mOrganizerAdapter);

                mTournaments = ((TournamentResponse) response).getTournaments();
                mOrganizerAdapter.setTournaments(mTournaments);
                mOrganizerAdapter.notifyDataSetChanged();
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

        mAdapter = new MainAdapter(mTournaments);
        mOrganizerAdapter = new MainOrganizerAdapter(mTournaments);

        mSessionManager = new SessionManager(getApplicationContext());
        token = "";
        member_type = 1;

        fetchDataTournament();
    }

    @Override
    public void onResume() {
        super.onResume();

        fetchDataTournament();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (member_type == 1) {
            menu.setGroupVisible(R.id.group_setting, true);
            MenuItem mFilter = menu.findItem(R.id.filter);
            mFilter.setVisible(false);
        } else {
            menu.setGroupVisible(R.id.group_setting, false);
        }
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
                mTournamentParticipantPresenter.doGetParticipantTournament(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    public void fetchDataTournament() {
        if (mSessionManager.isUserLoggedIn()) {
            if (mSessionManager.getUserLoggedIn().getMember_type() == 2) {
                token = mSessionManager.getTokenLoggedIn();
                member_type = 2;
            }
        } else {
            token = "";
            member_type = 1;
        }

        invalidateOptionsMenu();

        mProgressDialog.show();
        if (member_type == 1) {
            setTitle("List Tournament");
            mTournamentParticipantPresenter.doGetParticipantTournament(null);
        } else if (member_type == 2) {
            setTitle("My Tournament");
            mTournamentOrganizerPresenter.doGetOrganizerTournament(token);
        }
    }
}
