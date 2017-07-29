package com.tugasakhir.turnamensiamember.Model.Socket;

import com.tugasakhir.turnamensiamember.Model.Basic.Dota2LiveMatchPlayer;

import java.util.List;

/**
 * Created by Andrianto on 7/29/2017.
 */

public class Dota2LiveMatchPlayersUpdateEvent {
    private List<Dota2LiveMatchPlayer> players;

    public List<Dota2LiveMatchPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<Dota2LiveMatchPlayer> players) {
        this.players = players;
    }
}
