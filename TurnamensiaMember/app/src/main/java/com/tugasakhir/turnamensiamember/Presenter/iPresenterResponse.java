package com.tugasakhir.turnamensiamember.Presenter;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;

/**
 * Created by Asus on 25/06/2017.
 */

public interface iPresenterResponse {
    public void doSuccess(Response response);
    public void doFail(String message);
    public void doConnectionError(int message);
}
