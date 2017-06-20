package com.tugasakhir.turnamensiaorganizer.Presenter;

import com.tugasakhir.turnamensiaorganizer.Model.Basic.Response;

/**
 * Created by Asus on 11/06/2017.
 */

public interface iPresenterResponse {
    public void doSuccess(Response response);
    public void doFail(String message);
    public void doConnectionError(int message);
}
