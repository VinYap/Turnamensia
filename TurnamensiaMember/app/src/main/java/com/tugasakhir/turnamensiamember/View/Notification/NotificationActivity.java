package com.tugasakhir.turnamensiamember.View.Notification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.NotificationResponse;
import com.tugasakhir.turnamensiamember.Model.SessionManager;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountProfilePresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class NotificationActivity extends BaseActivity {
    private RecyclerView mNotificationRV;

    private ProgressDialog mProgressDialog;

    private AccountProfilePresenter mGetNotification;

    private SessionManager mSessionManager;
    private String token;

    private NotificationAdapter mNotificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_notification, mBaseLayout);

        setTitle("Notification");

        showUpCaretMenu();

        mNotificationRV = (RecyclerView) findViewById(R.id.notification);
        mNotificationRV.setLayoutManager(new LinearLayoutManager(this));
        mNotificationRV.setHasFixedSize(true);

        mGetNotification = new AccountProfilePresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                mProgressDialog.dismiss();

                mNotificationAdapter.setmNotifications(((NotificationResponse) response).getNotifications());
                mNotificationAdapter.notifyDataSetChanged();
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

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");

        mSessionManager = new SessionManager(this);
        token = mSessionManager.getTokenLoggedIn();

        mNotificationAdapter = new NotificationAdapter(token);
        mNotificationRV.setAdapter(mNotificationAdapter);

        mProgressDialog.show();
        mGetNotification.doGetMyNotification(token);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem mActionSettings = menu.findItem(R.id.action_settings);
        mActionSettings.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
