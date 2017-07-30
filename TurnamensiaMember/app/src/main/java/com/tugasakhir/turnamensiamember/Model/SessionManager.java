package com.tugasakhir.turnamensiamember.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tugasakhir.turnamensiamember.Model.Basic.User;
import com.tugasakhir.turnamensiamember.Model.Response.LoginResponse;

/**
 * Created by Asus on 12/07/2017.
 */

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    Context context;

    private final int PRIVATE_MODE = 0;
    private final String PREF_NAME = "UserSession";
    private final String KEY_IS_PREF_LOGIN = "isUserLogin";
    private final String KEY_TOKEN_DATA = "TokenData";
    private final String KEY_USER_DATA = "UserData";
    private final String BEARER = "Bearer ";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void doCreateSession(LoginResponse loginResponse) {
        editor.putBoolean(KEY_IS_PREF_LOGIN, true);
        editor.putString(KEY_TOKEN_DATA, loginResponse.getToken());
        editor.putString(KEY_USER_DATA, new Gson().toJson(loginResponse.getUser()));
        editor.commit();
    }

    public void doChangeTokenData(String token) {
        editor.putString(KEY_TOKEN_DATA, token);
        editor.commit();
    }

    public void doChangeUserData(User user) {
        User oldUser = this.getUserLoggedIn();
        if (oldUser != null) {
            user.setId(oldUser.getId());
            if (user.getName() == null) user.setName(oldUser.getName());
            if (user.getEmail() == null) user.setEmail(oldUser.getEmail());
            if (user.getSteam32_id() == null) user.setSteam32_id(oldUser.getSteam32_id());
            if (user.getImage() == null) user.setImage(oldUser.getImage());
            if (user.getMember_type() == null) user.setMember_type(oldUser.getMember_type());
        }
        editor.putString(KEY_USER_DATA, new Gson().toJson(user));
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_PREF_LOGIN, false);
    }

    public String getTokenLoggedIn() {
        String token = sharedPreferences.getString(KEY_TOKEN_DATA, "");
        if (token.equals("")) return token;
        return BEARER.concat(token);
    }

    public User getUserLoggedIn() {
        return new Gson().fromJson(sharedPreferences.getString(KEY_USER_DATA, ""), User.class);
    }

    public void doClearSession() {
        editor.clear();
        editor.commit();
    }
}
