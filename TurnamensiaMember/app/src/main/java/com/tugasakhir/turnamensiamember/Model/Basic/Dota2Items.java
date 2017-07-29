package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Andrianto on 7/28/2017.
 */

public class Dota2Items {
    private Long id;
    private String name;
    private String picture_file_name;
    private Integer item_order;

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

    public Integer getItem_order() {
        return item_order;
    }

    public void setItem_order(Integer item_order) {
        this.item_order = item_order;
    }
}
