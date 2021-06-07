package com.rozaanaonline.groceryshopping.sharePrefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefs {

    private final Context context;

    public SharePrefs(Context context) {        //constructor
        this.context = context;
    }

    private SharedPreferences getUserPreference() {
        return context.getSharedPreferences("com.rozaanaonline.groceryshopping",
                Activity.MODE_PRIVATE);
    }

    public void removeAllSP()               // mainly used for loging out (clearing all the data)
    {
        getUserPreference().edit().clear().apply();

    }
    // For creating session
    public Boolean isLoggedIn(){
        return getUserPreference().getBoolean("loggedin", false);
    }

    public void setLoggedIn(boolean b){
        getUserPreference().edit().putBoolean("loggedin",b).apply();
    }

    public String getFirstName(){
        return getUserPreference().getString("first_name","");
    }

    public void setFirstName(String firstName){
        getUserPreference().edit().putString("first_name",firstName).apply();
    }

    public String getLastName(){
        return getUserPreference().getString("last_name","");
    }

    public void setLastName(String firstName){
        getUserPreference().edit().putString("last_name",firstName).apply();
    }

    public String getEmail(){
        return getUserPreference().getString("email","");
    }

    public void setEmail(String email){
        getUserPreference().edit().putString("email",email).apply();
    }

    public String getMobileNum(){
        return getUserPreference().getString("mobile","");
    }

    public void setMobileNum(String mobile){
        getUserPreference().edit().putString("mobile",mobile).apply();
    }

    public String getToken(){
        return getUserPreference().getString("token","");
    }

    public void setToken(String token){
        getUserPreference().edit().putString("token",token).apply();
    }
}
