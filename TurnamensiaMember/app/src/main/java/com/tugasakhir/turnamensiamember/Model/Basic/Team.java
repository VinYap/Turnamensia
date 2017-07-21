package com.tugasakhir.turnamensiamember.Model.Basic;

import java.io.Serializable;

/**
 * Created by alvin on 7/7/2017.
 */

public class Team implements Serializable {
    private Long id;
    private String name;
    private String image;
    private String join_code;
    private Integer number_of_members;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJoin_code() {
        return join_code;
    }

    public void setJoin_code(String join_code) {
        this.join_code = join_code;
    }

    public Integer getNumber_of_members() {
        return number_of_members;
    }

    public void setNumber_of_members(Integer number_of_members) {
        this.number_of_members = number_of_members;
    }
}
