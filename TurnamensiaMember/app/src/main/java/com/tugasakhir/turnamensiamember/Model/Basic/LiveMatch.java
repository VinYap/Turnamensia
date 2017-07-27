package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Asus on 27/07/2017.
 */

public class LiveMatch {
    private Long id;
    private String series;
    private Integer round;
    private Integer spectators;
    private Integer duration;
    private String player_1;
    private String player_1_image;
    private Integer player_1_score;
    private String player_2;
    private String player_2_image;
    private Integer player_2_score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getSpectators() {
        return spectators;
    }

    public void setSpectators(Integer spectators) {
        this.spectators = spectators;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(String player_1) {
        this.player_1 = player_1;
    }

    public String getPlayer_1_image() {
        return player_1_image;
    }

    public void setPlayer_1_image(String player_1_image) {
        this.player_1_image = player_1_image;
    }

    public Integer getPlayer_1_score() {
        return player_1_score;
    }

    public void setPlayer_1_score(Integer player_1_score) {
        this.player_1_score = player_1_score;
    }

    public String getPlayer_2() {
        return player_2;
    }

    public void setPlayer_2(String player_2) {
        this.player_2 = player_2;
    }

    public String getPlayer_2_image() {
        return player_2_image;
    }

    public void setPlayer_2_image(String player_2_image) {
        this.player_2_image = player_2_image;
    }

    public Integer getPlayer_2_score() {
        return player_2_score;
    }

    public void setPlayer_2_score(Integer player_2_score) {
        this.player_2_score = player_2_score;
    }
}
