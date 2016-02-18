package com.trives.donutsshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.*;
import android.preference.PreferenceManager;

/**
 * Created by Trives on 15/02/2016.
 */
public class PreferenceData {
    static final String PREF_LOGGEDIN_USER_ID = "logged_in_user_id";
    static final String PREF_LOGGEDIN_USER = "logged_in_user";
    static final String PREF_USER_LOGGEDIN_STATUS = "logged_in_status";

    public static SharedPreferences getSharedPreferences(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setLoggedInUserId(Context ctx, String id)
    {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LOGGEDIN_USER_ID, id);
        editor.commit();
    }

    public static String getLoggedInUserId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_LOGGEDIN_USER_ID, "");
    }

    public static void setLoggedInUser(Context ctx, String user)
    {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LOGGEDIN_USER, user);
        editor.commit();
    }

    public static String getLoggedInUser(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_LOGGEDIN_USER, "");
    }

    public static void setUserLoggedInStatus(Context ctx, boolean status)
    {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_USER_LOGGEDIN_STATUS, status);
        editor.commit();
    }

    public static boolean getUserLoggedInStatus(Context ctx)
    {

        return getSharedPreferences(ctx).getBoolean(PREF_USER_LOGGEDIN_STATUS, false);
    }

    public static void clearLoggedInUser(Context ctx)
    {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_LOGGEDIN_USER_ID);
        editor.remove(PREF_LOGGEDIN_USER);
        editor.remove(PREF_USER_LOGGEDIN_STATUS);
        editor.commit();
    }

}
