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
        editor.putString(KEY_TOKEN_DATA, BEARER.concat(loginResponse.getToken()));
        editor.putString(KEY_USER_DATA, new Gson().toJson(loginResponse.getUser()));
        editor.commit();
    }

    public void doChangeTokenData(String token) {
        editor.putString(KEY_TOKEN_DATA, BEARER.concat(token));
        editor.commit();
    }

    public void doChangeUserData(User user) {
        editor.putString(KEY_USER_DATA, new Gson().toJson(user));
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_PREF_LOGIN, false);
    }

    public String getTokenLoggedIn() {
        return sharedPreferences.getString(KEY_TOKEN_DATA, "");
    }

    public User getUserLoggedIn() {
        return new Gson().fromJson(sharedPreferences.getString(KEY_USER_DATA, ""), User.class);
    }

    public void doClearSession() {
        editor.clear();
        editor.commit();
    }
}
