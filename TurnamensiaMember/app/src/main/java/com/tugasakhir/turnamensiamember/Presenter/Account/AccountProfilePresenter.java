package com.tugasakhir.turnamensiamember.Presenter.Account;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 16/07/2017.
 */

public class AccountProfilePresenter {
    iPresenterResponse iAccountProfileResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iAccountProfileResponse
     */
    public AccountProfilePresenter(iPresenterResponse iAccountProfileResponse) {
        this.iAccountProfileResponse = iAccountProfileResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param name
     * @param email
     * @param steamId
     */
    public void doUpdateParticipantAccountProfile(String name, String email, String steamId) {
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        data.put("steam32_id", steamId);

        ConnectionAPI.getInstance().getAPIModel().doUpdateParticipantAccountProfile(data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iAccountProfileResponse.doSuccess(response.body());
                    } else {
                        iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iAccountProfileResponse.doConnectionError(R.string.connection_error);
            }
        });
    }
}
