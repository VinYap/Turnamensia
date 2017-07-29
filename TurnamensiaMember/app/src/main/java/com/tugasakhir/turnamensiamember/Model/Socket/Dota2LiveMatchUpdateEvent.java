package com.tugasakhir.turnamensiamember.Model.Socket;

import com.tugasakhir.turnamensiamember.Model.Basic.Dota2Heroes;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatch;
import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchTeam;

import java.util.List;

/**
 * Created by Andrianto on 7/29/2017.
 */

public class Dota2LiveMatchUpdateEvent {
    private Dota2LiveMatch match;
    private Dota2LiveMatchTeam radiant;
    private List<Dota2Heroes> radiant_picks;
    private List<Dota2Heroes> radiant_bans;
    private Dota2LiveMatchTeam dire;
    private List<Dota2Heroes> dire_picks;
    private List<Dota2Heroes> dire_bans;

    public Dota2LiveMatch getMatch() {
        return match;
    }

    public void setMatch(Dota2LiveMatch match) {
        this.match = match;
    }

    public Dota2LiveMatchTeam getRadiant() {
        return radiant;
    }

    public void setRadiant(Dota2LiveMatchTeam radiant) {
        this.radiant = radiant;
    }

    public List<Dota2Heroes> getRadiant_picks() {
        return radiant_picks;
    }

    public void setRadiant_picks(List<Dota2Heroes> radiant_picks) {
        this.radiant_picks = radiant_picks;
    }

    public List<Dota2Heroes> getRadiant_bans() {
        return radiant_bans;
    }

    public void setRadiant_bans(List<Dota2Heroes> radiant_bans) {
        this.radiant_bans = radiant_bans;
    }

    public Dota2LiveMatchTeam getDire() {
        return dire;
    }

    public void setDire(Dota2LiveMatchTeam dire) {
        this.dire = dire;
    }

    public List<Dota2Heroes> getDire_picks() {
        return dire_picks;
    }

    public void setDire_picks(List<Dota2Heroes> dire_picks) {
        this.dire_picks = dire_picks;
    }

    public List<Dota2Heroes> getDire_bans() {
        return dire_bans;
    }

    public void setDire_bans(List<Dota2Heroes> dire_bans) {
        this.dire_bans = dire_bans;
    }
}
