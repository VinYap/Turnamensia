package com.tugasakhir.turnamensiamember.Model.Basic;

import java.math.BigDecimal;

/**
 * Created by Asus on 12/07/2017.
 */

public class User {
    private BigDecimal id;
    private String email;
    private String name;
    private String steam32_id;
    private String image;
    private Integer member_type;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getMember_type() {
        return member_type;
    }

    public void setMember_type(Integer member_type) {
        this.member_type = member_type;
    }
}
