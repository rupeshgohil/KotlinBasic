package aru.kotlinbasic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import aru.kotlinbasic.R;
import aru.kotlinbasic.app.MyApplication;

public class Preference {
    private static Preference preference;

    /**
     * Preference key for userId
     */
    private final String PREFERENCE_USER_ID = "USER_ID";
    private final String PREFERENCE_SUPPORT_USER = "SUPPORT_USER";
    public static final String PREFERENCE_DOCTOR_ID = "DOCTOR_ID";
    public static final String PREFERENCE_SUPPLIER_ID = "SUPPLIER_ID";
    public final String PREFERENCE_USER_IS_LOGIN = "USER_IS_LOGIN";
    public static final String PREFERENCE_LANG_ID = "LANG_ID";
    public static final String PREFERENCE_STORE_ID = "STORE_ID";

    public static final String PREFERENCE_UUID = "uuid";
    public static final String PREFERENCE_FCM_TOKEN = "fcm_token";


    private SharedPreferences sharedPreferences;

    private Preference() {
        sharedPreferences = MyApplication.getInstance().getSharedPreferences(MyApplication.getInstance().getString(R.string.app_name), Context.MODE_PRIVATE);
    }
    public static Preference getInstance() {
        if (preference == null) {
            preference = new Preference();
        }
        return preference;
    }
    public String getUserId() {
        return sharedPreferences.getString(PREFERENCE_USER_ID, "");
    }
    public void setUserId(final String userId) {
        sharedPreferences.edit().putString(PREFERENCE_USER_ID, userId).apply();
    }
    public String getSupportUser() {
        return sharedPreferences.getString(PREFERENCE_SUPPORT_USER, "");
    }
    public void setSupportUser(final String supportUser) {
        sharedPreferences.edit().putString(PREFERENCE_SUPPORT_USER, supportUser).apply();
    }
    public void setData(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getData(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }
    public void clearAllPreferenceData() {
        sharedPreferences.edit().clear().apply();
    }


    public void clearData() {
        sharedPreferences.edit().clear().apply();
    }
}
