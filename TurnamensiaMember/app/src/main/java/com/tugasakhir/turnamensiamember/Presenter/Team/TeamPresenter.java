package com.tugasakhir.turnamensiamember.Presenter.Team;

import android.text.TextUtils;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.AccountTeamResponse;
import com.tugasakhir.turnamensiamember.Model.Response.CountResponse;
import com.tugasakhir.turnamensiamember.Model.Response.MemberResponse;
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

public class TeamPresenter {
    iPresenterResponse iTeamResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iTeamResponse
     */
    public TeamPresenter(iPresenterResponse iTeamResponse) {
        this.iTeamResponse = iTeamResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param name
     */
    public void doGetParticipantTeam(String token, String name) {
        Map<String, String> data = new HashMap<>();
        if (name != null) data.put("name", name);

        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTeam(token, data).enqueue(new Callback<AccountTeamResponse>() {
            @Override
            public void onResponse(Call<AccountTeamResponse> call, retrofit2.Response<AccountTeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<AccountTeamResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doGetParticipantTeamSearchDetail(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTeamSearchDetail(token, teamId).enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, retrofit2.Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doGetParticipantTeamDetail(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTeamDetail(token, teamId).enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, retrofit2.Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
        data.put("with_join_password", TextUtils.isEmpty(joinCode) ? 0 : 1);
        data.put("join_password", joinCode);

        ConnectionAPI.getInstance().getAPIModel().doUpdateParticipantTeamProfile(token, teamId, data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
        data.put("with_join_password", TextUtils.isEmpty(joinCode) ? 0 : 1);
        data.put("join_password", joinCode);

        ConnectionAPI.getInstance().getAPIModel().doCreateParticipantTeam(token, data).enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, retrofit2.Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 201) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doLeaveParticipantTeam(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doLeaveParticipantTeam(token, teamId).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doDisbandParticipantTeam(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doDisbandParticipantTeam(token, teamId).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doGetParticipantTeamMember(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTeamMember(token, teamId).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, retrofit2.Response<MemberResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param teamId
     * @param joinCode
     */
    public void doJoinPartcipantTeam(String token, Long teamId, String joinCode) {
        Map<String, String> data = new HashMap<>();
        data.put("join_password", joinCode);

        ConnectionAPI.getInstance().getAPIModel().doJoinPartcipantTeam(token, teamId, data).enqueue(new Callback<CountResponse>() {
            @Override
            public void onResponse(Call<CountResponse> call, retrofit2.Response<CountResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<CountResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doAcceptPartcipantTeam(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doAcceptPartcipantTeam(token, teamId).enqueue(new Callback<CountResponse>() {
            @Override
            public void onResponse(Call<CountResponse> call, retrofit2.Response<CountResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<CountResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doRejectPartcipantTeam(String token, Long teamId) {
        ConnectionAPI.getInstance().getAPIModel().doRejectPartcipantTeam(token, teamId).enqueue(new Callback<CountResponse>() {
            @Override
            public void onResponse(Call<CountResponse> call, retrofit2.Response<CountResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<CountResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param teamId
     * @param searchKey
     */
    public void doGetParticipantTeamUninvitedMember(String token, Long teamId, String searchKey) {
        Map<String, String> data = new HashMap<>();
        if (searchKey != null) data.put("search_keyword", searchKey);

        ConnectionAPI.getInstance().getAPIModel().doGetParticipantTeamUninvitedMember(token, teamId, data).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, retrofit2.Response<MemberResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
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
    public void doInviteParticipantTeamMember(String token, Long teamId, Long memberId) {
        ConnectionAPI.getInstance().getAPIModel().doInviteParticipantTeamMember(token, teamId, memberId).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTeamResponse.doSuccess(response.body());
                    } else {
                        iTeamResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iTeamResponse.doFail(response.body().getMessage()[0]);
                    else iTeamResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iTeamResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
