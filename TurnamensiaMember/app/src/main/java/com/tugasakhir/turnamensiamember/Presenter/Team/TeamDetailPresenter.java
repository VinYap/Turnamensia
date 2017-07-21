package com.tugasakhir.turnamensiamember.Presenter.Team;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Response.TeamResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by alvin on 7/21/2017.
 */

public class TeamDetailPresenter {
    iPresenterResponse iTeamDetailResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iTeamDetailResponse
     */
    public TeamDetailPresenter(iPresenterResponse iTeamDetailResponse) {
        this.iTeamDetailResponse = iTeamDetailResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param teamId
     */
    public void doGetParticipantTeamDetail(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTeamDetail(token, teamId).enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, retrofit2.Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamDetailResponse.doSuccess(response.body());
                    } else {
                        iTeamDetailResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamDetailResponse.doFail(response.body().getMessage()[0]);
                    else iTeamDetailResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                iTeamDetailResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
