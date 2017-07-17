package com.tugasakhir.turnamensiamember.Model.API;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.AccountProfileResponse;
import com.tugasakhir.turnamensiamember.Model.Response.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Asus on 12/07/2017.
 */

public interface APIModel {
    /**
     * Routes From API
     */
    @POST("/api/participant/login")
    Call<LoginResponse> doParticipantLogin(@Body Map<String, String> data);

    @POST("/api/participant/register")
    Call<Response> doParticipantRegister(@Body Map<String, String> data);

    @GET("/api/participant/profile")
    Call<AccountProfileResponse> doGetParticipantAccountProfile(@Body Map<String, String> data);

    @PUT("/api/participant/profile")
    Call<Response> doUpdateParticipantAccountProfile(@Body Map<String, String> data);

    @POST("/api/organizer/login")
    Call<LoginResponse> doOrganizerLogin(@Body Map<String, String> data);
}
