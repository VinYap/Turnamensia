package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.OrganizerMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;

import java.util.List;
import java.util.Map;

/**
 * Created by Andrianto on 7/23/2017.
 */

public class OrganizerTournamentDetailResponse extends Response {
    private Map<String, List<OrganizerMatch>> matches;

    public Map<String, List<OrganizerMatch>> getMatches() {
        return matches;
    }

    public void setMatches(Map<String, List<OrganizerMatch>> matches) {
        this.matches = matches;
    }
}
