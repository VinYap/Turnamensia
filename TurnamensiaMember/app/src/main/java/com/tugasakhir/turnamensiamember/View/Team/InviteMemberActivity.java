package com.tugasakhir.turnamensiamember.View.Team;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.MemberResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Team.TeamPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import java.util.List;

import static com.tugasakhir.turnamensiamember.View.Account.AccountTeamViewHolder.TEAM_KEY;

public class InviteMemberActivity extends BaseActivity implements iPresenterResponse {
    private RecyclerView mMemberRV;

    private List<Member> members;
    private Long teamId;
    private String token;

    private ProgressDialog mProgressDialog;
    private SessionManager mSessionManager;
    private TeamPresenter mTeamPresenter;
    private InviteMemberAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_invite_member, mBaseLayout);

        showUpCaretMenu();

        setTitle("Invite Member");
        mMemberRV = (RecyclerView) findViewById(R.id.invite_member_rv);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);
        mTeamPresenter = new TeamPresenter(this);

        token = mSessionManager.getTokenLoggedIn();
        teamId = getIntent().getLongExtra(TEAM_KEY, -1);

        mMemberRV.setLayoutManager(new LinearLayoutManager(this));
        mMemberRV.setHasFixedSize(true);

        mAdapter = new InviteMemberAdapter(members, teamId);
        mMemberRV.setAdapter(mAdapter);

        mProgressDialog.show();
        mTeamPresenter.doGetParticipantTeamUninvitedMember(token, teamId, null);
    }

    @Override
    public void doSuccess(Response response) {
        mProgressDialog.dismiss();
        members = ((MemberResponse) response).getMembers();
        mAdapter.setMembers(members);
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
                mTeamPresenter.doGetParticipantTeamUninvitedMember(token, teamId, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
}
