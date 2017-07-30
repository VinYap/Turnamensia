package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Notification;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;

import java.util.List;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class NotificationResponse extends Response {
    private List<Notification> notifications;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
