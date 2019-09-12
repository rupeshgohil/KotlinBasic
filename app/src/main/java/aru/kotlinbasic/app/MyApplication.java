package aru.kotlinbasic.app;

import android.app.Application;

import aru.kotlinbasic.utils.AppSharedPreferences;

public class MyApplication extends Application {
    private static MyApplication jipApp;
    public static MyApplication getInstance() {
        return jipApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        jipApp = this;
        if (AppSharedPreferences.getInstance(getApplicationContext()).getString(AppSharedPreferences.PREFERENCE_LANGUAGE).equals("")) {
            AppSharedPreferences.getInstance(getApplicationContext()).setString(AppSharedPreferences.PREFERENCE_LANGUAGE, "en");
        }


    }
}
