package com.tugasakhir.turnamensiamember.View.Registration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.RegisterTeamResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Register.RegisterTournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.Main.MainViewHolder.TOURNAMENT_KEY;
import static com.tugasakhir.turnamensiamember.View.Main.MainViewHolder.TOURNAMENT_NAME;

public class RegistrationActivity extends BaseActivity implements iPresenterResponse {
    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private RegisterTournamentPresenter mRegisterTournamentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_registration, mBaseLayout);

        showUpCaretMenu();

        if (savedInstanceState !=  null) {
            return;
        }

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);
        mRegisterTournamentPresenter = new RegisterTournamentPresenter(this);

        String token = mSessionManager.getTokenLoggedIn();
        Long tournamentId = getIntent().getLongExtra(TOURNAMENT_KEY, -1);

        this.setTitle(getIntent().getStringExtra(TOURNAMENT_NAME) + " Registration");

        mProgressDialog.show();
        mRegisterTournamentPresenter.doGetParticipantRegisterTournamentTeam(token, tournamentId);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        RegisterTeamResponse newResponse = (RegisterTeamResponse) response;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_registration, SelectTeamFragment.newInstance(newResponse.getTeams(), newResponse.getTournament()))
                .commit();
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
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
