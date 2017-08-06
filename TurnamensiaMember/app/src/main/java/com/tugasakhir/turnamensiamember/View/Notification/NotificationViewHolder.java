package com.tugasakhir.turnamensiamember.View.Notification;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Notification;
import com.tugasakhir.turnamensiamember.Presenter.Account.AccountProfilePresenter;
import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.Team.TeamActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tugasakhir.turnamensiamember.View.Account.AccountTeamViewHolder.TEAM_KEY;
import static com.tugasakhir.turnamensiamember.View.MainTeam.MainTeamViewHolder.HAS_INVITATION;
import static com.tugasakhir.turnamensiamember.View.MainTeam.MainTeamViewHolder.IS_SEARCH;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mNotificationTV;
    private TextView mNotificationDateTV;
    private TextView mNotificationStatusTV;

    private AccountProfilePresenter mPostNotification;

    private String token;
    private Long teamId;
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
            this.teamId = notification.getMember_join_team().getTeam().getId();
        } else if (notification.getTeam_invitation() != null) {
            mNotificationTV.setText("Team " + notification.getTeam_invitation().getTeam().getName() + " sent you an invitation.");
            this.teamId = notification.getTeam_invitation().getTeam().getId();
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
        Context context = itemView.getContext();
        Intent intent = new Intent(context, TeamActivity.class);
        intent.putExtra(TEAM_KEY, teamId);
        intent.putExtra(IS_SEARCH, true);
        intent.putExtra(HAS_INVITATION, true);
        context.startActivity(intent);
    }
}
