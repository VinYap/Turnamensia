package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Asus on 26/06/2017.
 */

public class Tournament {
    private Long id;
    private String image;
    private String name;
    private Integer start_date;
    private Integer end_date;
    private Integer registration_closed;
    private Integer entry_fee;
    private String status;

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
}
