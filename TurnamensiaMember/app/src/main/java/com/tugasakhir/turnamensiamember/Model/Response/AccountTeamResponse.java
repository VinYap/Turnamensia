package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;

import java.util.List;

/**
 * Created by Asus on 19/07/2017.
 */

public class AccountTeamResponse extends Response {
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
