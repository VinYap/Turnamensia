package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Andrianto on 7/28/2017.
 */

public class Dota2Heroes {
    private Long id;
    private String name;
    private String picture_file_name;
    private Integer pick_order;
    private Integer ban_order;

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

    public String getPicture_file_name() {
        return picture_file_name;
    }

    public void setPicture_file_name(String picture_file_name) {
        this.picture_file_name = picture_file_name;
    }

    public Integer getPick_order() {
        return pick_order;
    }

    public void setPick_order(Integer pick_order) {
        this.pick_order = pick_order;
    }

    public Integer getBan_order() {
        return ban_order;
    }

    public void setBan_order(Integer ban_order) {
        this.ban_order = ban_order;
    }
}
