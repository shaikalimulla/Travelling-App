package com.example.ali.nativx;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ali on 2/20/2016.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabse;

    public UserLocalStore (Context context) {
        userLocalDatabse = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(String email){
        SharedPreferences.Editor spEditor = userLocalDatabse.edit();
        spEditor.putString("email", email);
        spEditor.commit();
    }

    public String getLoggedInUser() {
       String email = userLocalDatabse.getString("email", "");

       return email;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabse.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabse.getBoolean("loggedIn", false) == true)
            return true;
        else
            return false;
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabse.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
