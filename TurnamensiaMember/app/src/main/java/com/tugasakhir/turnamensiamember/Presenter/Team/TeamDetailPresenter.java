package com.tugasakhir.turnamensiamember.Presenter.Team;

import android.text.TextUtils;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.PictureResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TeamResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
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

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param teamId
     * @param name
     * @param joinCode
     */
    public void doUpdateParticipantTeamProfile(String token, Long teamId, String name, String joinCode) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("with_join_password", !TextUtils.isEmpty(joinCode));
        data.put("join_password", joinCode);

        ConnectionAPI.getInstance().getAPIModel().doUpdateParticipantTeamProfile(token, teamId, data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
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
            public void onFailure(Call<Response> call, Throwable t) {
                iTeamDetailResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param teamId
     * @param image
     */
    public void doUpdateParticipantTeamPicture(String token, Long teamId, MultipartBody.Part image) {
        ConnectionAPI.getInstance().getAPIModel().doUpdateParticipantTeamPicture(token, teamId, image).enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call, retrofit2.Response<PictureResponse> response) {
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
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                iTeamDetailResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param teamId
     */
    public void doDeleteParticipantTeamPicture(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doDeleteParticipantTeamPicture(token, teamId).enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call, retrofit2.Response<PictureResponse> response) {
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
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                iTeamDetailResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param name
     * @param joinCode
     */
    public void doCreateParticipantTeam(String token, String name, String joinCode) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("with_join_password", !TextUtils.isEmpty(joinCode));
        data.put("join_password", joinCode);

        ConnectionAPI.getInstance().getAPIModel().doCreateParticipantTeam(token, data).enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, retrofit2.Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 201) {
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

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param teamId
     * @param memberId
     */
    public void doDeleteParticipantTeamMember(String token, Long teamId, Long memberId) {
        ConnectionAPI.getInstance().getAPIModel().doDeleteParticipantTeamMember(token, teamId, memberId).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
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
            public void onFailure(Call<Response> call, Throwable t) {
                iTeamDetailResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
