package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Basic.TournamentRegistration;

/**
 * Created by Andrianto on 7/24/2017.
 */

public class QRScannerResultResponse extends Response {
    private Tournament tournament;
    private TournamentRegistration tournament_registration;
    private Team team;
    private Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
