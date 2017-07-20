package com.tugasakhir.turnamensiamember.Model.API;

import android.graphics.Bitmap;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.AccountProfileResponse;
import com.tugasakhir.turnamensiamember.Model.Response.AccountTeamResponse;
import com.tugasakhir.turnamensiamember.Model.Response.LoginResponse;
import com.tugasakhir.turnamensiamember.Model.Response.ProfilePictureResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TeamResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentResponse;

import java.math.BigDecimal;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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

    @GET("/api/participant/tournament")
    Call<TournamentResponse> doGetParticipantTournament(@QueryMap Map<String, Object> data);

    @GET("/api/participant/profile")
    Call<AccountProfileResponse> doGetParticipantAccountProfile(@Header("Authorization") String authorization);

    @PUT("/api/participant/profile")
    Call<Response> doUpdateParticipantAccountProfile(@Header("Authorization") String authorization, @Body Map<String, String> data);

    @PUT("/api/participant/password")
    Call<Response> doUpdateParticipantPassword(@Header("Authorization") String authorization, @Body Map<String, String> data);

    @POST("/api/participant/profile-picture")
    Call<ProfilePictureResponse> doUpdateParticipantProfilePicture(@Header("Authorization") String authorization, @Body Map<String, Bitmap> data);

    @DELETE("/api/participant/profile-picture")
    Call<ProfilePictureResponse> doDeleteParticipantProfilePicture(@Header("Authorization") String authorization);

    @GET("/api/participant/my-team")
    Call<AccountTeamResponse> doGetParticipantAccountTeam(@Header("Authorization") String authorization);

    @GET("/api/participant/my-team/{id}")
    Call<TeamResponse> doGetParticipantTeamProfile(@Header("Authorization") String authorization, @Path("id") BigDecimal id);

    @PUT("/api/participant/team/{id}")
    Call<Response> doUpdateParticipantTeamProfile(@Header("Authorization") String authorization, @Body Map<String, String> data, @Path("id") BigDecimal id);

    @POST("/api/organizer/login")
    Call<LoginResponse> doOrganizerLogin(@Body Map<String, String> data);
}
