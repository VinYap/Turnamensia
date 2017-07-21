package com.tugasakhir.turnamensiamember.Presenter.Authentication;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 15/07/2017.
 */

public class RegisterPresenter {
    iPresenterResponse iRegisterResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iRegisterResponse
     */
    public RegisterPresenter(iPresenterResponse iRegisterResponse) {
        this.iRegisterResponse = iRegisterResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param name
     * @param email
     * @param password
     * @param passwordConfirmation
     */
    public void doParticipantRegister(String name, String email, String password, String passwordConfirmation) {
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        data.put("password", password);
        data.put("password_confirmation", passwordConfirmation);

        ConnectionAPI.getInstance().getAPIModel().doParticipantRegister(data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 201) {
                        iRegisterResponse.doSuccess(response.body());
                    } else {
                        iRegisterResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iRegisterResponse.doFail(response.body().getMessage()[0]);
                    else iRegisterResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iRegisterResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
