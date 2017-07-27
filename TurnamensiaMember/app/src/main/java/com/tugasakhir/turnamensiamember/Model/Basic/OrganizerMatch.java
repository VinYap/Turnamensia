package com.tugasakhir.turnamensiamember.Model.Basic;

import org.parceler.Parcel;

/**
 * Created by Andrianto on 7/23/2017.
 */

@Parcel
public class OrganizerMatch {
    private Long id;
    private Long player_1_id;
    private String player_1;
    private String player_1_image;
    private Long player_2_id;
    private String player_2;
    private String player_2_image;
    private Long scheduled_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayer_1_id() {
        return player_1_id;
    }

    public void setPlayer_1_id(Long player_1_id) {
        this.player_1_id = player_1_id;
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

    public Long getPlayer_2_id() {
        return player_2_id;
    }

    public void setPlayer_2_id(Long player_2_id) {
        this.player_2_id = player_2_id;
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

    public Long getScheduled_date() {
        return scheduled_date;
    }

    public void setScheduled_date(Long scheduled_date) {
        this.scheduled_date = scheduled_date;
    }
}
