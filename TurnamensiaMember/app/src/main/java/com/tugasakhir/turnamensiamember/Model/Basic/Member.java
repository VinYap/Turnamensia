package com.tugasakhir.turnamensiamember.Model.Basic;

import org.parceler.Parcel;

/**
 * Created by alvin on 7/7/2017.
 */

@Parcel
public class Member {
    private Long id;
    private String name;
    private String steam32_id;
    private String image;
    private String identification_image;
    private String status;
    private Integer joined_at;
    private Integer attendances_status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteam32_id() {
        return steam32_id;
    }

    public void setSteam32_id(String steam32_id) {
        this.steam32_id = steam32_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdentification_image() {
        return identification_image;
    }

    public void setIdentification_image(String identification_image) {
        this.identification_image = identification_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJoined_at() {
        return joined_at;
    }

    public void setJoined_at(Integer joined_at) {
        this.joined_at = joined_at;
    }

    public Integer getAttendances_status() {
        return attendances_status;
    }

    public void setAttendances_status(Integer attendances_status) {
        this.attendances_status = attendances_status;
    }
}
