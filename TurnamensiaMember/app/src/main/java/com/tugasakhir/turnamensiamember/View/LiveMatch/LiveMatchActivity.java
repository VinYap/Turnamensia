package com.tugasakhir.turnamensiamember.View.LiveMatch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchTeam;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.Dota2MatchResponse;
import com.tugasakhir.turnamensiamember.Model.Socket.Dota2LiveMatchPlayersItemsUpdateEvent;
import com.tugasakhir.turnamensiamember.Model.Socket.Dota2LiveMatchPlayersUpdateEvent;
import com.tugasakhir.turnamensiamember.Model.Socket.Dota2LiveMatchUpdateEvent;
import com.tugasakhir.turnamensiamember.Presenter.Tournament.TournamentPresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;
import com.tugasakhir.turnamensiamember.View.Comment.CommentActivity;

import java.util.Timer;
import java.util.TimerTask;

import static com.tugasakhir.turnamensiamember.View.Tournament.TournamentLiveMatchViewHolder.LIVE_MATCH_KEY;
import static com.tugasakhir.turnamensiamember.View.Tournament.TournamentLiveMatchViewHolder.LIVE_MATCH_TOURNAMENT_NAME_KEY;

public class LiveMatchActivity extends BaseActivity {
    public static final String LIVE_MATCH_ID_KEY = "LiveMatchID";

    private TextView mMatchIDTV;
    private TextView mMatchGameTV;
    private TextView mRadiantTV;
    private ImageView mRadiantIV;
    private TextView mRadiantScoreTV;
    private TextView mDurationTV;
    private TextView mDireScoreTV;
    private ImageView mDireIV;
    private TextView mDireTV;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mCommentB;

    private ProgressDialog mProgressDialog;
    private TournamentPresenter mTournamentPresenter;
    private PickBanFragment mPickBanFragment;
    private PlayerStatFragment mPlayerStatFragment;

    private Long match_id;
    private Long duration;

    final Handler mHandler = new Handler();
    Timer mTimer;
    TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_live_match, mBaseLayout);

        setTitle(getIntent().getExtras().getString(LIVE_MATCH_TOURNAMENT_NAME_KEY, ""));

        showUpCaretMenu();

        mMatchIDTV = (TextView) findViewById(R.id.match_id);
        mMatchGameTV = (TextView) findViewById(R.id.match_game);
        mRadiantTV = (TextView) findViewById(R.id.radiant_text);
        mRadiantIV = (ImageView) findViewById(R.id.radiant_image);
        mRadiantScoreTV = (TextView) findViewById(R.id.radiant_score);
        mDurationTV = (TextView) findViewById(R.id.duration);
        mDireScoreTV = (TextView) findViewById(R.id.dire_score);
        mDireIV = (ImageView) findViewById(R.id.dire_image);
        mDireTV = (TextView) findViewById(R.id.dire_text);
        mTabLayout = (TabLayout) findViewById(R.id.statistic_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.statistic_view_pager);
        mCommentB = (Button) findViewById(R.id.live_match_comment);

        match_id = getIntent().getExtras().getLong(LIVE_MATCH_KEY, 0);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mTournamentPresenter = new TournamentPresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                Dota2MatchResponse dota2_match_response = ((Dota2MatchResponse) response);
                Dota2LiveMatch dota2_live_match = dota2_match_response.getDota2_live_match();
                Dota2LiveMatchTeam radiant = dota2_match_response.getRadiant();
                Dota2LiveMatchTeam dire = dota2_match_response.getDire();

                mMatchIDTV.setText("Match ID: " + dota2_live_match.getId());
                Integer games = 1 + radiant.getSeries_wins() + dire.getSeries_wins();
                String series_type = "Best of ";
                if (dota2_live_match.getSeries_type() == 0) {
                    series_type = series_type + "1";
                } else if (dota2_live_match.getSeries_type() == 1) {
                    series_type = series_type + "3";
                } else if (dota2_live_match.getSeries_type() == 2) {
                    series_type = series_type + "5";
                }
                mMatchGameTV.setText("Game " + games + "|" + series_type);
                if (radiant.getTournament_registration() != null) {
                    mRadiantTV.setText(radiant.getTournament_registration().getTeam().getName());
                    mPickBanFragment.updateHeader(1, radiant.getTournament_registration().getTeam().getName());
                    mPlayerStatFragment.updateHeader(1, radiant.getTournament_registration().getTeam().getName());
                    if (radiant.getTournament_registration().getTeam().getPicture_file_name() != null) {
                        String radiant_image = ConnectionAPI.getBaseUrl() + "/storage/team/" + radiant.getTournament_registration().getTeam().getPicture_file_name();
                        Picasso.with(getApplicationContext()).load(radiant_image).into(mRadiantIV);
                        mPlayerStatFragment.updateImageHeader(1, radiant_image);
                    }
                }
                duration = dota2_live_match.getDuration();
                formatDuration();
                if (radiant.getMatches_result() == null && dire.getMatches_result() == null) {
                    mTimer = new Timer(false);
                    mTimerTask = new TimerTask() {
                        @Override
                        public void run() {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    duration++;
                                    formatDuration();
                                }
                            });
                        }
                    };
                    mTimer.schedule(mTimerTask, 1000, 1000);
                }
                mRadiantScoreTV.setText("" + radiant.getScore());
                if (dire.getTournament_registration() != null) {
                    mDireTV.setText(dire.getTournament_registration().getTeam().getName());
                    mPickBanFragment.updateHeader(2, dire.getTournament_registration().getTeam().getName());
                    mPlayerStatFragment.updateHeader(2, dire.getTournament_registration().getTeam().getName());
                    if (dire.getTournament_registration().getTeam().getPicture_file_name() != null) {
                        String dire_image = ConnectionAPI.getBaseUrl() + "/storage/team/" + dire.getTournament_registration().getTeam().getPicture_file_name();
                        Picasso.with(getApplicationContext()).load(dire_image).into(mDireIV);
                        mPlayerStatFragment.updateImageHeader(2, dire_image);
                    }
                }
                mDireScoreTV.setText("" + dire.getScore());

                mPickBanFragment.update(1, radiant.getHeroes_pick(), radiant.getHeroes_ban());
                mPickBanFragment.update(2, dire.getHeroes_pick(), dire.getHeroes_ban());

                mPlayerStatFragment.updateStatistic(1, radiant.getDota2_live_match_players());
                mPlayerStatFragment.updateItem(1, radiant.getDota2_live_match_players());
                mPlayerStatFragment.updateStatistic(2, dire.getDota2_live_match_players());
                mPlayerStatFragment.updateItem(2, dire.getDota2_live_match_players());
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

        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        Pusher pusher = new Pusher("bb383a21ca6b3b72c3a2", options);

        Channel channel = pusher.subscribe("dota2-live-match" + match_id);

        channel.bind("update", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Dota2LiveMatchUpdateEvent data_obj = gson.fromJson(data, Dota2LiveMatchUpdateEvent.class);
                        Dota2LiveMatch match = data_obj.getMatch();
                        Dota2LiveMatchTeam radiant = data_obj.getRadiant();
                        Dota2LiveMatchTeam dire = data_obj.getDire();

                        if (radiant.getMatches_result() != null && dire.getMatches_result() != null) {
                            duration = match.getDuration();
                            formatDuration();
                            mTimer.cancel();
                        }
                        mRadiantScoreTV.setText("" + radiant.getScore());
                        mDireScoreTV.setText("" + dire.getScore());

                        mPickBanFragment.update(1, data_obj.getRadiant_picks(), data_obj.getRadiant_bans());
                        mPickBanFragment.update(2, data_obj.getDire_picks(), data_obj.getDire_bans());
                    }
                });
            }
        });

        channel.bind("radiant_players_update", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Dota2LiveMatchPlayersUpdateEvent data_obj = gson.fromJson(data, Dota2LiveMatchPlayersUpdateEvent.class);

                        mPlayerStatFragment.updateStatistic(1, data_obj.getPlayers());
                    }
                });
            }
        });

        channel.bind("dire_players_update", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Dota2LiveMatchPlayersUpdateEvent data_obj = gson.fromJson(data, Dota2LiveMatchPlayersUpdateEvent.class);

                        mPlayerStatFragment.updateStatistic(2, data_obj.getPlayers());
                    }
                });
            }
        });

        channel.bind("players_items_update", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Dota2LiveMatchPlayersItemsUpdateEvent data_obj = gson.fromJson(data, Dota2LiveMatchPlayersItemsUpdateEvent.class);

                        mPlayerStatFragment.updateItem(1, data_obj.getRadiant_items());
                        mPlayerStatFragment.updateItem(2, data_obj.getDire_items());
                    }
                });
            }
        });

        pusher.connect();

        mPickBanFragment = PickBanFragment.newInstance();
        mPlayerStatFragment = PlayerStatFragment.newInstance();

        LiveMatchPagerAdapter adapter = new LiveMatchPagerAdapter(getSupportFragmentManager(), mPickBanFragment, mPlayerStatFragment);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mCommentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommentActivity.class);
                intent.putExtra(LIVE_MATCH_ID_KEY, match_id);
                startActivity(intent);
            }
        });

        mProgressDialog.show();
        mTournamentPresenter.doGetDota2Match(match_id);
    }

    private void formatDuration() {
        String duration_second = String.valueOf(duration % 60);
        if (duration_second.length() == 1) {
            duration_second = "0" + duration_second;
        }
        String duration_minute = String.valueOf(Math.round(Math.floor(duration / 60)));
        if (duration_minute.length() == 1) {
            duration_minute = "0" + duration_minute;
        }
        mDurationTV.setText(duration_minute + ":" + duration_second);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
