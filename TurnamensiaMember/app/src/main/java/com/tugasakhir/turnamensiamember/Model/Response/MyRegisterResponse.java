package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.MyRegistration;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;

import java.util.List;

/**
 * Created by Asus on 30/07/2017.
 */

public class MyRegisterResponse extends Response {
    private List<MyRegistration> myRegistrations;

    public List<MyRegistration> getMyRegistrations() {
        return myRegistrations;
    }

    public void setMyRegistrations(List<MyRegistration> myRegistrations) {
        this.myRegistrations = myRegistrations;
    }
}
