package com.tugasakhir.turnamensiamember.Model.Basic;

import org.parceler.Parcel;

/**
 * Created by Andrianto on 7/25/2017.
 */

@Parcel
public class TournamentRegistration {
    private Long id;
    private Long register_at;
    private Team team;
    private String name;
    private Integer team_size;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegister_at() {
        return register_at;
    }

    public void setRegister_at(Long register_at) {
        this.register_at = register_at;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeam_size() {
        return team_size;
    }

    public void setTeam_size(Integer team_size) {
        this.team_size = team_size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
