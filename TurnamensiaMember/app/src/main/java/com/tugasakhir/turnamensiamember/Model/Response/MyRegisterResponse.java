package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.MyRegistration;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;

import java.util.List;

/**
 * Created by Asus on 30/07/2017.
 */

public class MyRegisterResponse extends Response {
    private List<MyRegistration> registrations;

    public List<MyRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<MyRegistration> registrations) {
        this.registrations = registrations;
    }
}
