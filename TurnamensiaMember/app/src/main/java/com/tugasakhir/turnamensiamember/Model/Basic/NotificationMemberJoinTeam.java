package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Andrianto on 7/30/2017.
 */

public class NotificationMemberJoinTeam {
    private Team team;
    private Member member;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
