package com.tugasakhir.turnamensiamember.Presenter.Authentication;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Response.LoginResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 12/07/2017.
 */

public class LoginPresenter {
    iPresenterResponse iLoginResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iLoginResponse
     */
    public LoginPresenter(iPresenterResponse iLoginResponse) {
        this.iLoginResponse = iLoginResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param email
     * @param password
     */
    public void doParticipantLogin(String email, String password) {
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);
        data.put("token_in_json", "1");

        ConnectionAPI.getInstance().getAPIModel().doParticipantLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iLoginResponse.doSuccess(response.body());
                    } else {
                        iLoginResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iLoginResponse.doFail(response.body().getMessage()[0]);
                    else iLoginResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iLoginResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param email
     * @param password
     */
    public void doOrganizerLogin(String email, String password) {
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);
        data.put("token_in_json", "1");

        ConnectionAPI.getInstance().getAPIModel().doOrganizerLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iLoginResponse.doSuccess(response.body());
                    } else {
                        iLoginResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iLoginResponse.doFail(response.body().getMessage()[0]);
                    else iLoginResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iLoginResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
