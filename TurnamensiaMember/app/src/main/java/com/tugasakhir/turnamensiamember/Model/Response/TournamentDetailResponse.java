package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.LiveMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.OrganizerMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;

import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 27/07/2017.
 */


public class TournamentDetailResponse extends Response {
    private Tournament tournament;
    private Map<String, List<OrganizerMatch>> matches;
    private List<LiveMatch> live_matches_json;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Map<String, List<OrganizerMatch>> getMatches() {
        return matches;
    }

    public void setMatches(Map<String, List<OrganizerMatch>> matches) {
        this.matches = matches;
    }

    public List<LiveMatch> getLive_matches_json() {
        return live_matches_json;
    }

    public void setLive_matches_json(List<LiveMatch> live_matches_json) {
        this.live_matches_json = live_matches_json;
    }
}
