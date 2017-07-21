package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by alvin on 7/7/2017.
 */

public class Member {
    private Long id;
    private String name;
    private String steam32_id;
    private String image;
    private String status;
    private Integer joined_at;

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
}
