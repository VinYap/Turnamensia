package com.tugasakhir.turnamensiamember.View.Notification;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Notification;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountProfilePresenter;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    private String token;
    private List<Notification> mNotifications;

    private AccountProfilePresenter mPostNotification;

    public NotificationAdapter(String token) {
        this.token = token;

        mPostNotification = new AccountProfilePresenter(new iPresenterResponse() {
            @Override
            public void doSuccess(Response response) {
                /* Do Nothing */
            }

            @Override
            public void doFail(String message) {
                /* Do Nothing */
            }

            @Override
            public void doConnectionError(int message) {
                /* Do Nothing */
            }
        });
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        Notification mNotification = mNotifications.get(position);
        holder.bindHolder(mPostNotification, token, mNotification);
    }

    @Override
    public int getItemCount() {
        if (mNotifications != null) {
            return mNotifications.size();
        } else {
            return 0;
        }
    }

    public void setmNotifications(List<Notification> mNotifications) {
        this.mNotifications = mNotifications;
    }
}
