package aru.kotlinbasic.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by viraj on 26/10/17.
 */

public class AppSharedPreferences {
    public static final String PREF_SHOW_TUT = "intro";
    public static final String PREF_USER_DATA = "user_data";
    public static final String PREFERENCE_LANGUAGE = "language";
    // TODO: 26/10/17 :-  Change app name to name which you want.
    private static final String PREFERENCE_FILE_KEY = "app";
    public static final String FCMTOKEN = "tokan";
    public static AppSharedPreferences mPrefData;
    private static SharedPreferences mSharedPref;


    private AppSharedPreferences() {
    }

    /**
     * Get single tone variable for the application preference
     *
     * @param context
     * @return :- AppSharedPreferences instance.
     */
    public static AppSharedPreferences getInstance(Context context) {
        if (null == mSharedPref) {
            mSharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        }
        if (null == mPrefData) {
            mPrefData = new AppSharedPreferences();
        }
        return mPrefData;
    }

    /**
     * get value of the key in shared preference
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return mSharedPref.getString(key, "");
    }


    public boolean getBoolean(String key) {
        return mSharedPref.getBoolean(key, false);
    }

    /**
     * get value of the key in shared preference
     *
     * @param key
     * @return
     */
    public String contains(String key) {
        return mSharedPref.getString(key, "");
    }

    /**
     * Set the value for key in shared preference.
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

}


