package com.tugasakhir.turnamensiamember.View.Notification;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Notification;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountProfilePresenter;
import com.tugasakhir.turnamensiamember.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mNotificationTV;
    private TextView mNotificationDateTV;
    private TextView mNotificationStatusTV;

    private AccountProfilePresenter mPostNotification;

    private String token;
    private Long notification_id;
    private Integer post;

    public NotificationViewHolder(View itemView) {
        super(itemView);

        mNotificationTV = (TextView) itemView.findViewById(R.id.notification_text);
        mNotificationDateTV = (TextView) itemView.findViewById(R.id.notification_date);
        mNotificationStatusTV = (TextView) itemView.findViewById(R.id.notification_status);

        itemView.setOnClickListener(this);
    }

    public void bindHolder(AccountProfilePresenter mPostNofitication, String token, Notification notification) {
        this.mPostNotification = mPostNofitication;
        this.token = token;

        notification_id = notification.getId();

        if (notification.getMember_join_team() != null) {
            mNotificationTV.setText(notification.getMember_join_team().getMember().getName() + " is part of the team " + notification.getMember_join_team().getTeam().getName() + " now.");
        } else if (notification.getTeam_invitation() != null) {
            mNotificationTV.setText("Team " + notification.getTeam_invitation().getTeam().getName() + " sent you an invitation.");
        }
        mNotificationDateTV.setText(new SimpleDateFormat("dd MMMM yyy, HH:mm").format(new Date(notification.getCreated_at() * 1000)));
        post = 1;
        if (notification.getRead_status() == 1) {
            mNotificationStatusTV.setVisibility(View.GONE);
            post = 0;
        }
    }

    @Override
    public void onClick(View v) {
        if (post == 1) {
            mPostNotification.doPostMyNotification(token, notification_id);
            mNotificationStatusTV.setVisibility(View.GONE);
            post = 0;
        }
    }
}
