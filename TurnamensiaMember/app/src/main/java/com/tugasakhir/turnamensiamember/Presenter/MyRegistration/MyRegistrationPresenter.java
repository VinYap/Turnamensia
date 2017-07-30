package com.tugasakhir.turnamensiamember.Presenter.MyRegistration;

import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Response.MyRegisterResponse;
import com.tugasakhir.turnamensiamember.Model.Response.PictureResponse;
import com.tugasakhir.turnamensiamember.Model.Response.RegistrationConfirmationResponse;
import com.tugasakhir.turnamensiamember.Presenter.iPresenterResponse;
import com.tugasakhir.turnamensiamember.R;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Asus on 30/07/2017.
 */

public class MyRegistrationPresenter {
    iPresenterResponse iMyRegistrationResponse;

    /**
     * For Communicating Between View and Presenter
     *
     * @param iMyRegistrationResponse
     */
    public MyRegistrationPresenter(iPresenterResponse iMyRegistrationResponse) {
        this.iMyRegistrationResponse = iMyRegistrationResponse;
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     */
    public void doGetParticipantMyRegister(String token) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantMyRegister(token).enqueue(new Callback<MyRegisterResponse>() {
            @Override
            public void onResponse(Call<MyRegisterResponse> call, retrofit2.Response<MyRegisterResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iMyRegistrationResponse.doSuccess(response.body());
                    } else {
                        iMyRegistrationResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iMyRegistrationResponse.doFail(response.body().getMessage()[0]);
                    else iMyRegistrationResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<MyRegisterResponse> call, Throwable t) {
                iMyRegistrationResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param transferId
     */
    public void doGetParticipantConfirmPayment(String token, Long transferId) {
        ConnectionAPI.getInstance().getAPIModel().doGetParticipantConfirmPayment(token, transferId).enqueue(new Callback<RegistrationConfirmationResponse>() {
            @Override
            public void onResponse(Call<RegistrationConfirmationResponse> call, retrofit2.Response<RegistrationConfirmationResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iMyRegistrationResponse.doSuccess(response.body());
                    } else {
                        iMyRegistrationResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iMyRegistrationResponse.doFail(response.body().getMessage()[0]);
                    else iMyRegistrationResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<RegistrationConfirmationResponse> call, Throwable t) {
                iMyRegistrationResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }

    /**
     * For Communicating Between Apps and API
     *
     * @param token
     * @param transferId
     * @param image
     * @param name
     * @param bankId
     */
    public void doParticipantConfirmPayment(String token, Long transferId, MultipartBody.Part image, String name, Long bankId) {
        Map<String, RequestBody> data = new HashMap<String, RequestBody>();
        data.put("name", RequestBody.create(MediaType.parse("text/plain"), name));
        data.put("bank", RequestBody.create(MediaType.parse("text/plain"), bankId.toString()));

        ConnectionAPI.getInstance().getAPIModel().doParticipantConfirmPayment(token, transferId, image, data).enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call, retrofit2.Response<PictureResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        iMyRegistrationResponse.doSuccess(response.body());
                    } else {
                        iMyRegistrationResponse.doFail(response.body().getMessage()[0]);
                    }
                } else {
                    if (response.body() != null)
                        iMyRegistrationResponse.doFail(response.body().getMessage()[0]);
                    else iMyRegistrationResponse.doConnectionError(R.string.connection_error);
                }
            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                iMyRegistrationResponse.doConnectionError(R.string.connection_error);
                t.printStackTrace();
            }
        });
    }
}
