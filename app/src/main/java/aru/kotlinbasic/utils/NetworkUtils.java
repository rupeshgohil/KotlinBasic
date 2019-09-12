package aru.kotlinbasic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Purpose of this Class is to check internet connection of phone and perform actions on user's input
 */
public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
    }



}