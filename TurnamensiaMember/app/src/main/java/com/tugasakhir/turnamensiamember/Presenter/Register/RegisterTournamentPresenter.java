package com.tugasakhir.turnamensiamember.Presenter.Register;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.RegisterTeamResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 28/07/2017.
 */

public class RegisterTournamentPresenter {
    iPresenterResponse iRegisterTournamentResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iRegisterTournamentResponse
     */
    public RegisterTournamentPresenter(iPresenterResponse iRegisterTournamentResponse) {
        this.iRegisterTournamentResponse = iRegisterTournamentResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param tournamentId
     */
    public void doGetParticipantRegisterTournamentTeam(String token, Long tournamentId) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantRegisterTournamentTeam(token, tournamentId).enqueue(new Callback<RegisterTeamResponse>() {
            @Override
            public void onResponse(Call<RegisterTeamResponse> call, retrofit2.Response<RegisterTeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iRegisterTournamentResponse.doSuccess(response.body());
                    } else {
                        iRegisterTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iRegisterTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iRegisterTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<RegisterTeamResponse> call, Throwable t) {
                iRegisterTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param tournamentId
     * @param teamId
     * @param membersId
     */
    public void doParticipantRegisterTournament(String token, Long tournamentId, Long teamId, List<Long> membersId) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("team", teamId);
        data.put("members", membersId);

        ConnectionAPI.getInstance().getAPIModel().doParticipantRegisterTournament(token, tournamentId, data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 201) {
                        iRegisterTournamentResponse.doSuccess(response.body());
                    } else {
                        iRegisterTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iRegisterTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iRegisterTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iRegisterTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
