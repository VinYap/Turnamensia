package com.tugasakhir.turnamensiamember.Model.API;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Response.AccountProfileResponse;
import com.tugasakhir.turnamensiamember.Model.Response.AccountTeamResponse;
import com.tugasakhir.turnamensiamember.Model.Response.CommentResponse;
import com.tugasakhir.turnamensiamember.Model.Response.Dota2MatchResponse;
import com.tugasakhir.turnamensiamember.Model.Response.LoginResponse;
import com.tugasakhir.turnamensiamember.Model.Response.MemberResponse;
import com.tugasakhir.turnamensiamember.Model.Response.MatchTeamAttendanceResponse;
import com.tugasakhir.turnamensiamember.Model.Response.OrganizerTournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.Response.PictureResponse;
import com.tugasakhir.turnamensiamember.Model.Response.QRScannerResultResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TeamResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentDetailResponse;
import com.tugasakhir.turnamensiamember.Model.Response.TournamentResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("/api/participant/tournament/{id}/detail")
    Call<TournamentDetailResponse> doGetParticipantTournamentDetail(@Path("id") Long id);

    @GET("/api/participant/profile")
    Call<AccountProfileResponse> doGetParticipantAccountProfile(@Header("Authorization") String authorization);

    @PUT("/api/participant/profile")
    Call<Response> doUpdateParticipantAccountProfile(@Header("Authorization") String authorization, @Body Map<String, String> data);

    @PUT("/api/participant/password")
    Call<Response> doUpdateParticipantPassword(@Header("Authorization") String authorization, @Body Map<String, String> data);

    @Multipart
    @POST("/api/participant/profile-picture")
    Call<PictureResponse> doUpdateParticipantProfilePicture(@Header("Authorization") String authorization, @Part MultipartBody.Part image);

    @DELETE("/api/participant/profile-picture")
    Call<PictureResponse> doDeleteParticipantProfilePicture(@Header("Authorization") String authorization);

    @GET("/api/participant/my-identification")
    Call<PictureResponse> doGetParticipantIdentification(@Header("Authorization") String authorization);

    @Multipart
    @POST("/api/participant/identification")
    Call<PictureResponse> doUpdateParticipantIdentification(@Header("Authorization") String authorization, @Part MultipartBody.Part image);

    @GET("/api/participant/my-tournament")
    Call<Response> doGetParticipantMyTournament(@Header("Authorization") String authorization);

    @GET("/api/participant/my-team")
    Call<AccountTeamResponse> doGetParticipantAccountTeam(@Header("Authorization") String authorization);

    @POST("/api/participant/team")
    Call<TeamResponse> doCreateParticipantTeam(@Header("Authorization") String authorization, @Body Map<String, Object> data);

    @GET("/api/participant/my-team/{id}")
    Call<TeamResponse> doGetParticipantTeamDetail(@Header("Authorization") String authorization, @Path("id") Long id);

    @PUT("/api/participant/team/{id}")
    Call<Response> doUpdateParticipantTeamProfile(@Header("Authorization") String authorization, @Path("id") Long id, @Body Map<String, Object> data);

    @Multipart
    @POST("/api/participant/team/{id}/picture")
    Call<PictureResponse> doUpdateParticipantTeamPicture(@Header("Authorization") String authorization, @Path("id") Long id, @Part MultipartBody.Part image);

    @DELETE("/api/participant/team/{id}/picture")
    Call<PictureResponse> doDeleteParticipantTeamPicture(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("/api/participant/team/{id}/kick-member/{member_id}")
    Call<Response> doDeleteParticipantTeamMember(@Header("Authorization") String authorization, @Path("id") Long id, @Path("member_id") Long memberId);

    @GET("/api/participant/team/{id}/member")
    Call<MemberResponse> doGetParticipantTeamMember(@Header("Authorization") String authorization, @Path("id") Long id);

    @POST("/api/organizer/login")
    Call<LoginResponse> doOrganizerLogin(@Body Map<String, String> data);

    @GET("/api/organizer/my-tournament")
    Call<TournamentResponse> doGetOrganizerTournament(@Header("Authorization") String authorization);

    @GET("/api/organizer/my-tournament/{id}")
    Call<OrganizerTournamentDetailResponse> doGetOrganizerTournamentDetail(@Header("Authorization") String authorization, @Path("id") Long tournament_id);

    @GET("api/organizer/match/{id}/team-attendance")
    Call<MatchTeamAttendanceResponse> doGetMatchTeamAttendance(@Header("Authorization") String authorization, @Path("id") Long match_id, @QueryMap Map<String, Long> data);

    @GET("api/organizer/match/{id}/attendance")
    Call<QRScannerResultResponse> doGetMatchAttendance(@Header("Authorization") String authorization, @Path("id") Long match_id, @QueryMap Map<String, String> data);

    @POST("api/organizer/match/{id}/attendance")
    Call<Response> doPostMatchAttendance(@Header("Authorization") String authorization, @Path("id") Long match_id, @Body Map<String, String> data);

    @GET("api/participant/dota-2/match/{id}")
    Call<Dota2MatchResponse> doGetDota2Match(@Path("id") Long match_id);

    @GET("api/participant/dota-2/match/{id}/comment")
    Call<CommentResponse> doGetDota2MatchComment(@Header("Authorization") String authorization, @Path("id") Long match_id);

    @POST("api/participant/dota-2/match/{id}/comment")
    Call<Response> doPostDota2MatchComment(@Header("Authorization") String authorization, @Path("id") Long match_id, @Body Map<String, String> data);
}
