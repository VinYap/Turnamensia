package com.tugasakhir.turnamensiamember.Presenter.MyTournament;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournamentPresenter {
    iPresenterResponse iMyTournamentResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iMyTournamentResponse
     */
    public MyTournamentPresenter(iPresenterResponse iMyTournamentResponse) {
        this.iMyTournamentResponse = iMyTournamentResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     */
    public void doGetParticipantMyTournament(String token) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantMyTournament(token).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iMyTournamentResponse.doSuccess(response.body());
                    } else {
                        iMyTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iMyTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iMyTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iMyTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
