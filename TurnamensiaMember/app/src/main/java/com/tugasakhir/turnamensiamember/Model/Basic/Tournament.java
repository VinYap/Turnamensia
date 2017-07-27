package com.tugasakhir.turnamensiamember.Model.Basic;

import org.parceler.Parcel;

/**
 * Created by Asus on 26/06/2017.
 */

@Parcel
public class Tournament {
    private Long id;
    private String image;
    private String name;
    private String description;
    private String challonges_url;
    private String city;
    private String address;
    private Integer max_participant;
    private Integer team_size;
    private String rules;
    private String prize_1st;
    private String prize_2nd;
    private String prize_3rd;
    private String prize_other;
    private Integer start_date;
    private Integer end_date;
    private Integer registration_closed;
    private Integer entry_fee;
    private String status;
    private Integer number_of_registers;
    private String type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChallonges_url() {
        return challonges_url;
    }

    public void setChallonges_url(String challonges_url) {
        this.challonges_url = challonges_url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMax_participant() {
        return max_participant;
    }

    public void setMax_participant(Integer max_participant) {
        this.max_participant = max_participant;
    }

    public Integer getTeam_size() {
        return team_size;
    }

    public void setTeam_size(Integer team_size) {
        this.team_size = team_size;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getPrize_1st() {
        return prize_1st;
    }

    public void setPrize_1st(String prize_1st) {
        this.prize_1st = prize_1st;
    }

    public String getPrize_2nd() {
        return prize_2nd;
    }

    public void setPrize_2nd(String prize_2nd) {
        this.prize_2nd = prize_2nd;
    }

    public String getPrize_3rd() {
        return prize_3rd;
    }

    public void setPrize_3rd(String prize_3rd) {
        this.prize_3rd = prize_3rd;
    }

    public String getPrize_other() {
        return prize_other;
    }

    public void setPrize_other(String prize_other) {
        this.prize_other = prize_other;
    }

    public Integer getStart_date() {
        return start_date;
    }

    public void setStart_date(Integer start_date) {
        this.start_date = start_date;
    }

    public Integer getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Integer end_date) {
        this.end_date = end_date;
    }

    public Integer getRegistration_closed() {
        return registration_closed;
    }

    public void setRegistration_closed(Integer registration_closed) {
        this.registration_closed = registration_closed;
    }

    public Integer getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(Integer entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber_of_registers() {
        return number_of_registers;
    }

    public void setNumber_of_registers(Integer number_of_registers) {
        this.number_of_registers = number_of_registers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
