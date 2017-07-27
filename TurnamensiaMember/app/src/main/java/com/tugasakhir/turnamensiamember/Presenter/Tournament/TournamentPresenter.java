package com.tugasakhir.turnamensiamember.Presenter.Tournament;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 19/07/2017.
 */

public class TournamentPresenter {
    iPresenterResponse iTournamentResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iTournamentResponse
     */
    public TournamentPresenter(iPresenterResponse iTournamentResponse) {
        this.iTournamentResponse = iTournamentResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     */
    public void doGetParticipantTournament() {
        Map<String, Object> data = new HashMap<>();
//        data.put("name", null);
//        data.put("status", null);
//        data.put("order", null);
//        data.put("price", null);
//        data.put("start_date", null);
//        data.put("city", null);

        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTournament(data).enqueue(new Callback<TournamentResponse>() {
            @Override
            public void onResponse(Call<TournamentResponse> call, retrofit2.Response<TournamentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<TournamentResponse> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     */
    public void doGetParticipantTournamentDetail(Long id) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTournamentDetail(id).enqueue(new Callback<TournamentDetailResponse>() {
            @Override
            public void onResponse(Call<TournamentDetailResponse> call, retrofit2.Response<TournamentDetailResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<TournamentDetailResponse> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
