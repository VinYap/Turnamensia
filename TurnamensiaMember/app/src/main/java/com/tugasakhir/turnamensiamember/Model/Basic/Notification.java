package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class Notification {
    private Long id;
    private Integer read_status;
    private NotificationMemberJoinTeam member_join_team;
    private NotificationTeamInvitation team_invitation;
    private Long created_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRead_status() {
        return read_status;
    }

    public void setRead_status(Integer read_status) {
        this.read_status = read_status;
    }

    public NotificationMemberJoinTeam getMember_join_team() {
        return member_join_team;
    }

    public void setMember_join_team(NotificationMemberJoinTeam member_join_team) {
        this.member_join_team = member_join_team;
    }

    public NotificationTeamInvitation getTeam_invitation() {
        return team_invitation;
    }

    public void setTeam_invitation(NotificationTeamInvitation team_invitation) {
        this.team_invitation = team_invitation;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }
}
