package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Basic.TournamentRegistration;

import java.util.List;

/**
 * Created by Andrianto on 7/25/2017.
 */

public class MatchTeamAttendanceResponse extends Response {
    private Tournament tournament;
    private TournamentRegistration tournament_registration;
    private Team team;
    private List<Member> members;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public TournamentRegistration getTournament_registration() {
        return tournament_registration;
    }

    public void setTournament_registration(TournamentRegistration tournament_registration) {
        this.tournament_registration = tournament_registration;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
