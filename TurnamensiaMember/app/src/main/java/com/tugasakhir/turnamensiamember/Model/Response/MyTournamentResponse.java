package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.MyTournament;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;

import java.util.List;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournamentResponse extends Response {
    private List<MyTournament> in_progress_tournaments;
    private List<MyTournament> completed_tournaments;

    public List<MyTournament> getIn_progress_tournaments() {
        return in_progress_tournaments;
    }

    public void setIn_progress_tournaments(List<MyTournament> in_progress_tournaments) {
        this.in_progress_tournaments = in_progress_tournaments;
    }

    public List<MyTournament> getCompleted_tournaments() {
        return completed_tournaments;
    }

    public void setCompleted_tournaments(List<MyTournament> completed_tournaments) {
        this.completed_tournaments = completed_tournaments;
    }
}
