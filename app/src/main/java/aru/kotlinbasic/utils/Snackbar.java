package aru.kotlinbasic.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zestbrains_3 on 24/8/18.
 */

public class Snackbar {

    public static android.support.design.widget.Snackbar showSnackBar(final Context context, final View view, final boolean isError, final String message) {

        if (view == null) {
            return null;
        }

        final android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar.make(view, message, android.support.design.widget.Snackbar.LENGTH_LONG);
        final View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, isError ? android.R.color.holo_red_dark : android.R.color.holo_blue_light));
        final TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);
        snackbar.show();
        return snackbar;
    }
    public static android.support.design.widget.Snackbar showSnackBar(final Context context, final View view, final boolean isError, final String message, final String defaultMessage) {

        if (view == null) {
            return null;
        }

        final android.support.design.widget.Snackbar snackbar = android.support.design.widget.Snackbar.make(view, TextUtils.isEmpty(message) ? defaultMessage : message, android.support.design.widget.Snackbar.LENGTH_LONG);
        final View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, isError ? android.R.color.holo_red_dark : android.R.color.holo_blue_light));
        final TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);
        snackbar.show();
        return snackbar;
    }
}
