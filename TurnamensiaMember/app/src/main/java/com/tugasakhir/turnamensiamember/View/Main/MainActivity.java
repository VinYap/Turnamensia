package com.tugasakhir.turnamensiamember.View.Main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentResponse;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements iPresenterResponse {
    private RecyclerView mTournamentRV;

    private List<Tournament> mTournaments;

    private ProgressDialog mProgressDialog;
    private TournamentPresenter mTournamentPresenter;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, mBaseLayout);

        mTournamentRV = (RecyclerView) findViewById(R.id.main_tournament);
        mTournamentRV.setLayoutManager(new LinearLayoutManager(this));
        mTournamentRV.setHasFixedSize(true);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentPresenter = new TournamentPresenter(this);

        mProgressDialog.show();
        mTournamentPresenter.doGetParticipantTournament();

        mAdapter = new MainAdapter(mTournaments);
        mTournamentRV.setAdapter(mAdapter);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        mTournaments = ((TournamentResponse) response).getTournaments();
        mAdapter.setTournaments(mTournaments);
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
}
