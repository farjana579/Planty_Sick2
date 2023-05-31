package com.example.plantysick;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
    }
    public void saveSession(String user_name){
        //save session whenever user is logged in
        String userName = user_name;
        editor.putString(SESSION_KEY,  userName).commit();

    }
    public String getSession(){
      return sharedPreferences.getString(SESSION_KEY,"1");
     }
     public void removeSession(){
    editor.clear().commit();
     }
}
