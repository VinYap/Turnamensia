package com.tugasakhir.turnamensiamember.Presenter.MyRegister;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Response.MyRegisterResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 30/07/2017.
 */

public class MyRegisterPresenter {
    iPresenterResponse iMyRegisterResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iMyRegisterResponse
     */
    public MyRegisterPresenter(iPresenterResponse iMyRegisterResponse) {
        this.iMyRegisterResponse = iMyRegisterResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     */
    public void doGetParticipantMyRegister(String token) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantMyRegister(token).enqueue(new Callback<MyRegisterResponse>() {
            @Override
            public void onResponse(Call<MyRegisterResponse> call, retrofit2.Response<MyRegisterResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iMyRegisterResponse.doSuccess(response.body());
                    } else {
                        iMyRegisterResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iMyRegisterResponse.doFail(response.body().getMessage()[0]);
                    else iMyRegisterResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<MyRegisterResponse> call, Throwable t) {
                iMyRegisterResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
