package com.tugasakhir.turnamensiamember.View.Comment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.CommentResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Model.Socket.CommentUpdateEvent;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

import static com.tugasakhir.turnamensiamember.View.LiveMatch.LiveMatchActivity.LIVE_MATCH_ID_KEY;

public class CommentActivity extends BaseActivity {
    private RecyclerView mCommentRV;
    private LinearLayout mLinearLayout1;
    private EditText mCommentET;
    private ImageButton mCommentIB;

    private ProgressDialog mProgressDialog;

    private TournamentPresenter mGetComment;
    private TournamentPresenter mPostComment;

    private SessionManager mSessionManager;

    private Long match_id;
    private String token;

    private CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_comment, mBaseLayout);

        setTitle("EPICENTER 2017");

        showUpCaretMenu();

        mCommentRV = (RecyclerView) findViewById(R.id.comment);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.comment_linear_layout_1);
        mCommentET = (EditText) findViewById(R.id.comment_text);
        mCommentIB = (ImageButton) findViewById(R.id.comment_button);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mGetComment = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                mCommentAdapter.setmComments(((CommentResponse) response).getDota2_live_match_comments());
                mCommentAdapter.notifyDataSetChanged();
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
        mPostComment = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();
                mCommentET.setText("");
                Toast.makeText(getApplicationContext(), response.getMessage()[0], Toast.LENGTH_SHORT).show();
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

        mSessionManager = new SessionManager(this);

        match_id = getIntent().getLongExtra(LIVE_MATCH_ID_KEY, 0);
        if (mSessionManager.isUserLoggedIn()) {
            token = mSessionManager.getTokenLoggedIn();
        } else {
            mLinearLayout1.setVisibility(View.GONE);
        }

        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        Pusher pusher = new Pusher("bb383a21ca6b3b72c3a2", options);

        Channel channel = pusher.subscribe("dota2-live-match" + match_id);

        channel.bind("comment_update", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Debug Socket", "run: " + data);
                        Gson gson = new Gson();
                        CommentUpdateEvent data_obj = gson.fromJson(data, CommentUpdateEvent.class);

                        mCommentAdapter.addtoTopofList(data_obj.getComment_mobile());
                        mCommentAdapter.notifyItemInserted(0);
                    }
                });
            }
        });

        pusher.connect();

        mCommentRV.setLayoutManager(new LinearLayoutManager(this));
        mCommentRV.setHasFixedSize(true);
        mCommentAdapter = new CommentAdapter();
        mCommentRV.setAdapter(mCommentAdapter);
        mCommentIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mCommentET.getText().toString())) {
                    Toast.makeText(v.getContext(), "Comment field is required.", Toast.LENGTH_LONG).show();
                } else {
                    mProgressDialog.show();
                    mPostComment.doPostDota2MatchComment(token, match_id, mCommentET.getText().toString());
                }
            }
        });

        mProgressDialog.show();
        mGetComment.doGetDota2MatchComment(token, match_id);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group_setting, false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
