package com.tugasakhir.turnamensiamember.Model.Basic;

import java.util.List;

/**
 * Created by Asus on 30/07/2017.
 */

public class MyRegistration {
    private String team_name;
    private List<TournamentRegistration> tournaments_registrations;

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public List<TournamentRegistration> getTournaments_registrations() {
        return tournaments_registrations;
    }

    public void setTournaments_registrations(List<TournamentRegistration> tournaments_registrations) {
        this.tournaments_registrations = tournaments_registrations;
    }
}
