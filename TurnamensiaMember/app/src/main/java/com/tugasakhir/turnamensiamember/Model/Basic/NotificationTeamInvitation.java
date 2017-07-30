package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class NotificationTeamInvitation {
    private Team team;
    private Integer invitation_status;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getInvitation_status() {
        return invitation_status;
    }

    public void setInvitation_status(Integer invitation_status) {
        this.invitation_status = invitation_status;
    }
}
