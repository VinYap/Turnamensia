package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchTeam;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;

/**
 * Created by Andrianto on 7/28/2017.
 */

public class Dota2MatchResponse extends Response {
    private Dota2LiveMatch dota2_live_match;
    private Dota2LiveMatchTeam radiant;
    private Dota2LiveMatchTeam dire;

    public Dota2LiveMatch getDota2_live_match() {
        return dota2_live_match;
    }

    public void setDota2_live_match(Dota2LiveMatch dota2_live_match) {
        this.dota2_live_match = dota2_live_match;
    }

    public Dota2LiveMatchTeam getRadiant() {
        return radiant;
    }

    public void setRadiant(Dota2LiveMatchTeam radiant) {
        this.radiant = radiant;
    }

    public Dota2LiveMatchTeam getDire() {
        return dire;
    }

    public void setDire(Dota2LiveMatchTeam dire) {
        this.dire = dire;
    }
}
