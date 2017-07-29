package com.tugasakhir.turnamensiamember.Model.Socket;

import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchPlayer;

import java.util.List;

/**
 * Created by Andrianto on 7/29/2017.
 */

public class Dota2LiveMatchPlayersItemsUpdateEvent {
    private List<Dota2LiveMatchPlayer> radiant_items;
    private List<Dota2LiveMatchPlayer> dire_items;

    public List<Dota2LiveMatchPlayer> getRadiant_items() {
        return radiant_items;
    }

    public void setRadiant_items(List<Dota2LiveMatchPlayer> radiant_items) {
        this.radiant_items = radiant_items;
    }

    public List<Dota2LiveMatchPlayer> getDire_items() {
        return dire_items;
    }

    public void setDire_items(List<Dota2LiveMatchPlayer> dire_items) {
        this.dire_items = dire_items;
    }
}
