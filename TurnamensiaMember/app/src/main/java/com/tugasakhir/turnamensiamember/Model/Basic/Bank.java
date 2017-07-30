package com.tugasakhir.turnamensiamember.Model.Basic;

/**
 * Created by Asus on 30/07/2017.
 */

public class Bank {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }

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
}
