package com.tugasakhir.turnamensiamember.Model.Basic;

import java.util.List;

/**
 * Created by Andrianto on 7/28/2017.
 */

public class Dota2LiveMatchTeam {
    private Long id;
    private String dota2_teams_name;
    private Integer series_wins;
    private Integer score;
    private Integer matches_result;
    private TournamentRegistration tournament_registration;
    private List<Dota2Heroes> heroes_pick;
    private List<Dota2Heroes> heroes_ban;
    private List<Dota2LiveMatchPlayer> dota2_live_match_players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDota2_teams_name() {
        return dota2_teams_name;
    }

    public void setDota2_teams_name(String dota2_teams_name) {
        this.dota2_teams_name = dota2_teams_name;
    }

    public Integer getSeries_wins() {
        return series_wins;
    }

    public void setSeries_wins(Integer series_wins) {
        this.series_wins = series_wins;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMatches_result() {
        return matches_result;
    }

    public void setMatches_result(Integer matches_result) {
        this.matches_result = matches_result;
    }

    public TournamentRegistration getTournament_registration() {
        return tournament_registration;
    }

    public void setTournament_registration(TournamentRegistration tournament_registration) {
        this.tournament_registration = tournament_registration;
    }

    public List<Dota2Heroes> getHeroes_pick() {
        return heroes_pick;
    }

    public void setHeroes_pick(List<Dota2Heroes> heroes_pick) {
        this.heroes_pick = heroes_pick;
    }

    public List<Dota2Heroes> getHeroes_ban() {
        return heroes_ban;
    }

    public void setHeroes_ban(List<Dota2Heroes> heroes_ban) {
        this.heroes_ban = heroes_ban;
    }

    public List<Dota2LiveMatchPlayer> getDota2_live_match_players() {
        return dota2_live_match_players;
    }

    public void setDota2_live_match_players(List<Dota2LiveMatchPlayer> dota2_live_match_players) {
        this.dota2_live_match_players = dota2_live_match_players;
    }
}
