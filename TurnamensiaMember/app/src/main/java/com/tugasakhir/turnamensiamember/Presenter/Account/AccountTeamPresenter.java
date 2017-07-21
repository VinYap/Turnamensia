package com.tugasakhir.turnamensiamember.Presenter.Account;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Response.AccountTeamResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by alvin on 7/21/2017.
 */

public class AccountTeamPresenter {
    iPresenterResponse iAccountTeamResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iAccountTeamResponse
     */
    public AccountTeamPresenter(iPresenterResponse iAccountTeamResponse) {
        this.iAccountTeamResponse = iAccountTeamResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     */
    public void doGetParticipantAccountTeam(String token) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantAccountTeam(token).enqueue(new Callback<AccountTeamResponse>() {
            @Override
            public void onResponse(Call<AccountTeamResponse> call, retrofit2.Response<AccountTeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iAccountTeamResponse.doSuccess(response.body());
                    } else {
                        iAccountTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iAccountTeamResponse.doFail(response.body().getMessage()[0]);
                    else iAccountTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<AccountTeamResponse> call, Throwable t) {
                iAccountTeamResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
