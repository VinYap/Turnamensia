package com.tugasakhir.turnamensiamember.Presenter.Tournament;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.CommentResponse;
import com.tugasakhir.turnamensiamember.Model.Response.Dota2MatchResponse;
import com.tugasakhir.turnamensiamember.Model.Response.MatchTeamAttendanceResponse;
import com.tugasakhir.turnamensiamember.Model.Response.OrganizerTournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.Response.QRScannerResultResponse;
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
     */
    public void doGetParticipantTournament(String name) {
        Map<String, Object> data = new HashMap<>();
        if (name != null) data.put("name", name);
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
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
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

    public void doGetOrganizerTournament(String token) {
        ConnectionAPI.getInstance().getAPIModel().doGetOrganizerTournament(token).enqueue(new Callback<TournamentResponse>() {
            @Override
            public void onResponse(Call<TournamentResponse> call, retrofit2.Response<TournamentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
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

    public void doGetOrganizerTournamentDetail(String token, Long tournament_id) {
        ConnectionAPI.getInstance().getAPIModel().doGetOrganizerTournamentDetail(token, tournament_id).enqueue(new Callback<OrganizerTournamentDetailResponse>() {
            @Override
            public void onResponse(Call<OrganizerTournamentDetailResponse> call, retrofit2.Response<OrganizerTournamentDetailResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<OrganizerTournamentDetailResponse> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    public void doGetMatchTeamAttendance(String token, Long match_id, Long tournament_registration_id) {
        Map<String, Long> data = new HashMap<>();
        data.put("tournament_registration_id", tournament_registration_id);

        ConnectionAPI.getInstance().getAPIModel().doGetMatchTeamAttendance(token, match_id, data).enqueue(new Callback<MatchTeamAttendanceResponse>() {
            @Override
            public void onResponse(Call<MatchTeamAttendanceResponse> call, retrofit2.Response<MatchTeamAttendanceResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<MatchTeamAttendanceResponse> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    public void doGetMatchAttendance(String token, Long match_id, String qr_identifier) {
        Map<String, String> data = new HashMap<>();
        data.put("qr_identifier", qr_identifier);

        ConnectionAPI.getInstance().getAPIModel().doGetMatchAttendance(token, match_id, data).enqueue(new Callback<QRScannerResultResponse>() {
            @Override
            public void onResponse(Call<QRScannerResultResponse> call, retrofit2.Response<QRScannerResultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<QRScannerResultResponse> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    public void doPostMatchAttendance(String token, Long match_id, String qr_identifier) {
        Map<String, String> data = new HashMap<>();
        data.put("qr_identifier", qr_identifier);

        ConnectionAPI.getInstance().getAPIModel().doPostMatchAttendance(token, match_id, data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    public void doGetDota2Match(Long id) {
        ConnectionAPI.getInstance().getAPIModel().doGetDota2Match(id).enqueue(new Callback<Dota2MatchResponse>() {
            @Override
            public void onResponse(Call<Dota2MatchResponse> call, retrofit2.Response<Dota2MatchResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Dota2MatchResponse> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    public void doGetDota2MatchComment(String token, Long id) {
        ConnectionAPI.getInstance().getAPIModel().doGetDota2MatchComment(token, id).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, retrofit2.Response<CommentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    public void doPostDota2MatchComment(String token, Long id, String comment) {
        Map<String, String> data = new HashMap<>();
        data.put("comment", comment);

        ConnectionAPI.getInstance().getAPIModel().doPostDota2MatchComment(token, id, data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iTournamentResponse.doSuccess(response.body());
                    } else {
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
                    else iTournamentResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iTournamentResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
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
                } else {
                    if (response.body() != null)
                        iTournamentResponse.doFail(response.body().getMessage()[0]);
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
