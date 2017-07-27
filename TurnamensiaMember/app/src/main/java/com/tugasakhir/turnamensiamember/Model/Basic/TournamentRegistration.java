package com.tugasakhir.turnamensiamember.Model.Basic;

import org.parceler.Parcel;

/**
 * Created by Andrianto on 7/25/2017.
 */

@Parcel
public class TournamentRegistration {
    private Long id;
    private Long register_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegister_at() {
        return register_at;
    }

    public void setRegister_at(Long register_at) {
        this.register_at = register_at;
    }
}
