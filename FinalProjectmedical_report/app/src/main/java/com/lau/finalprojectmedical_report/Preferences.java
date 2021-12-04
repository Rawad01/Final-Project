package com.lau.finalprojectmedical_report;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


public class Preferences {

    private static final String sharedPr = "simplifiedcodingsharedpref";
    private static final String sharedUs = "keyusername";
    private static final String sharedE = "keyemail";
    private static final String sharedId = "keyid";
    private static Preferences mInstance;
    private static Context context;

    private Preferences(Context con) {
        context = con;
    }

    public static synchronized Preferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preferences(context);
        }
        return mInstance;
    }

    public void userLogin(users user) {
        SharedPreferences sp = context.getSharedPreferences(sharedPr, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(sharedId, user.getId());
        editor.putString(sharedUs, user.getUsername());
        editor.putString(sharedE, user.getEmail());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences spr = context.getSharedPreferences(sharedPr, Context.MODE_PRIVATE);
        return spr.getString(sharedUs, null) != null;
    }

    public users getUser() {
        SharedPreferences sp = context.getSharedPreferences(sharedPr, Context.MODE_PRIVATE);
        return new users(
                sp.getInt(sharedId, -1),
                sp.getString(sharedUs, null),
                sp.getString(sharedE, null)
        );
    }

    public void logout() {
        SharedPreferences spr = context.getSharedPreferences(sharedPr, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spr.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, SigninActivity.class));
    }
}
