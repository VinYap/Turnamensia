package com.tugasakhir.turnamensiamember.Presenter.Account;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.AccountProfileResponse;
import com.tugasakhir.turnamensiamember.Model.Response.ProfilePictureResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
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
     * @param token
     * @param name
     * @param email
     * @param steamId
     */
    public void doUpdateParticipantAccountProfile(String token, String name, String email, String steamId) {
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        data.put("steam32_id", steamId);

        ConnectionAPI.getInstance().getAPIModel().doUpdateParticipantAccountProfile(token, data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iAccountProfileResponse.doSuccess(response.body());
                    } else {
                        iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    else iAccountProfileResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iAccountProfileResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     */
    public void doGetParticipantAccountProfile(String token) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantAccountProfile(token).enqueue(new Callback<AccountProfileResponse>() {
            @Override
            public void onResponse(Call<AccountProfileResponse> call, retrofit2.Response<AccountProfileResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iAccountProfileResponse.doSuccess(response.body());
                    } else {
                        iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    else iAccountProfileResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<AccountProfileResponse> call, Throwable t) {
                iAccountProfileResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param oldPassword
     * @param newPassword
     * @param newConfirmPassword
     */
    public void doUpdateParticipantPassword(String token, String oldPassword, String newPassword, String newConfirmPassword) {
        Map<String, String> data = new HashMap<>();
        data.put("old_password", oldPassword);
        data.put("new_password", newPassword);
        data.put("new_password_confirmation", newConfirmPassword);

        ConnectionAPI.getInstance().getAPIModel().doUpdateParticipantPassword(token, data).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iAccountProfileResponse.doSuccess(response.body());
                    } else {
                        iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    else iAccountProfileResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                iAccountProfileResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param image
     */
    public void doUpdateParticipantProfilePicture(String token, MultipartBody.Part image) {
        ConnectionAPI.getInstance().getAPIModel().doUpdateParticipantProfilePicture(token, image).enqueue(new Callback<ProfilePictureResponse>() {
            @Override
            public void onResponse(Call<ProfilePictureResponse> call, retrofit2.Response<ProfilePictureResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iAccountProfileResponse.doSuccess(response.body());
                    } else {
                        iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    else iAccountProfileResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<ProfilePictureResponse> call, Throwable t) {
                iAccountProfileResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     */
    public void doDeleteParticipantProfilePicture(String token) {
        ConnectionAPI.getInstance().getAPIModel().doDeleteParticipantProfilePicture(token).enqueue(new Callback<ProfilePictureResponse>() {
            @Override
            public void onResponse(Call<ProfilePictureResponse> call, retrofit2.Response<ProfilePictureResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iAccountProfileResponse.doSuccess(response.body());
                    } else {
                        iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    }
                }
                else {
                    if (response.body() != null) iAccountProfileResponse.doFail(response.body().getMessage()[0]);
                    else iAccountProfileResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<ProfilePictureResponse> call, Throwable t) {
                iAccountProfileResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}