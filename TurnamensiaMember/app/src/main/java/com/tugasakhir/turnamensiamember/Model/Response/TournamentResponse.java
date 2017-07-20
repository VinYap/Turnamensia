package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;

import java.util.List;

/**
 * Created by Asus on 19/07/2017.
 */

public class TournamentResponse extends Response {
    private List<Tournament> tournaments;

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
