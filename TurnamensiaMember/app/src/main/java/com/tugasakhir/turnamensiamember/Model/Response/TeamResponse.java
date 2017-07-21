package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;

import java.util.List;

/**
 * Created by Asus on 19/07/2017.
 */

public class TeamResponse extends Response {
    private Team team;
    private Boolean is_leader;
    private List<Member> teams_details;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Boolean getIs_leader() {
        return is_leader;
    }

    public void setIs_leader(Boolean is_leader) {
        this.is_leader = is_leader;
    }

    public List<Member> getTeams_details() {
        return teams_details;
    }

    public void setTeams_details(List<Member> teams_details) {
        this.teams_details = teams_details;
    }
}
