package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Match;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;

import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 27/07/2017.
 */

public class TournamentDetailResponse extends Response {
    private Tournament tournament;
    private Map<String, List<Match>> matches;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Map<String, List<Match>> getMatches() {
        return matches;
    }

    public void setMatches(Map<String, List<Match>> matches) {
        this.matches = matches;
    }
}
