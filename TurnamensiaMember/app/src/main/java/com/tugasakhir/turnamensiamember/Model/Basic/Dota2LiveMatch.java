package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Andrianto on 7/28/2017.
 */

public class Dota2LiveMatch {
    private Long id;
    private Integer series_type;
    private Long spectators;
    private Long duration;
    private Integer roshan_respawn_timer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeries_type() {
        return series_type;
    }

    public void setSeries_type(Integer series_type) {
        this.series_type = series_type;
    }

    public Long getSpectators() {
        return spectators;
    }

    public void setSpectators(Long spectators) {
        this.spectators = spectators;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getRoshan_respawn_timer() {
        return roshan_respawn_timer;
    }

    public void setRoshan_respawn_timer(Integer roshan_respawn_timer) {
        this.roshan_respawn_timer = roshan_respawn_timer;
    }
}
