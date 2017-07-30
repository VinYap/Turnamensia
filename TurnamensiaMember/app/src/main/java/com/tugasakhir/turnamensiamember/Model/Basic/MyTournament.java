package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournament {
    private Long id;
    private String image;
    private String tournament_name;
    private String team_name;
    private Long start_date;
    private Long end_date;
    private String qr_identifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Long getStart_date() {
        return start_date;
    }

    public void setStart_date(Long start_date) {
        this.start_date = start_date;
    }

    public Long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Long end_date) {
        this.end_date = end_date;
    }

    public String getQr_identifier() {
        return qr_identifier;
    }

    public void setQr_identifier(String qr_identifier) {
        this.qr_identifier = qr_identifier;
    }
}
