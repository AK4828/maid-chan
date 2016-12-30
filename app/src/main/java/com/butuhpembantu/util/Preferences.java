package com.butuhpembantu.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.butuhpembantu.application.ButuhPembantuApplication;

/**
 * Created by akm on 12/30/16.
 */

public class Preferences {

    public static final String FIRST_APP_RUNNING = "first_app_running";
    public static final String LOGGED_IN = "logged_in";
    private SharedPreferences preferences;


    public static Preferences getInstance(){
        Preferences preferences = new Preferences();
        preferences.preferences = PreferenceManager.getDefaultSharedPreferences(ButuhPembantuApplication.getInstance());

        return preferences;
    }

    public SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public static void setFirstAppRunningStatus(boolean status){
        getInstance().getEditor()
                .putBoolean(FIRST_APP_RUNNING, status)
                .commit();
    }

    public boolean getFirstAppRunningStatus() {

        return getInstance().getPreferences().getBoolean(FIRST_APP_RUNNING, true);
    }

    public static void setLoginStatus(boolean status){
        getInstance().getEditor()
                .putBoolean(LOGGED_IN, status)
                .commit();
    }

}
