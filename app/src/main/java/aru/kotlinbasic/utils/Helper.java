package aru.kotlinbasic.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import aru.kotlinbasic.R;

public class Helper {
    private static final String TAG = "Helper";
    public static View rootView;
    private static String CountryCode = "";
    private static Typeface FONT_REGULAR;
    private static Typeface FONT_BOLD;
    private static Typeface FONT_SEMI_BOLD;

    /**
     * Displaying error in application.
     *
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * This for displaying log in application
     *
     * @param msg
     */
    public static void logDisplay(String msg) {
        logDisplay(TAG, "logDisplay", msg);
    }

    /**
     * This for displaying log in application
     *
     * @param tag
     * @param msg
     */
    public static void logDisplay(String tag, String msg) {
        Log.d(tag, "logDisplay: " + msg);
    }


    private static void logDisplay(String tag, String methodName, String msg) {
        Log.d(tag, methodName + ": " + msg);
    }

    private static void initializeFonts(final Context context) {
        FONT_REGULAR = Typeface.createFromAsset(context.getAssets(), "fonts/myfont_medium.otf");
        FONT_BOLD = Typeface.createFromAsset(context.getAssets(), "fonts/myfont_bold.otf");
        FONT_SEMI_BOLD = Typeface.createFromAsset(context.getAssets(), "fonts/myfont_bold.otf");
    }

    public static void overrideFontsBold(final Context context, final View v) {
        overrideFonts(context, v, FONT_BOLD);
    }

    public static void overrideFontsSemiBold(final Context context, final View v) {
        overrideFonts(context, v, FONT_SEMI_BOLD);
    }

    private static void overrideFonts(final Context context, final View v) {
        overrideFonts(context, v, FONT_REGULAR);
    }

    private static void overrideFonts(final Context context, final View v, Typeface fontTypeFace) {
        if (FONT_REGULAR == null)
            initializeFonts(context);
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(fontTypeFace);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
    }


    public static String localToGMT() {

        String currentDate = "";

        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date gmt = new Date(sdf.format(date));
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
            sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
            currentDate = sdf1.format(date);
            Log.e(gmt.toString() + "DATEPASSS  : ", currentDate);

        } catch (Exception e) {
            currentDate = "2018-12-20 06:11:05";
        }

        return currentDate;
    }


    public static void hideKeyboard(Activity context) {
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showError(Context context, String msg) {
        /*hideSnackBar();
         View view = rootView;
         if (view == null)
            view = v;
         snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
         snackbar.show();*/
        showToast(context, msg);
    }

   /* public static SpannableStringBuilder setTextWithColor(Context context, String str1, String str2) {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        String red = str1;
        //String red = "Don\'t have an account? ";
        SpannableString redSpannable = new SpannableString(red);
        redSpannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.transparant)), 0, red.length(), 0);
        builder.append(redSpannable);


        // String white = "SIGN IN";
        String white = str2;
        SpannableString whiteSpannable = new SpannableString(white);
        whiteSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, white.length(), 0);
        builder.append(whiteSpannable);

        return builder;
    }*/


    public static boolean checkEmailValidation(Context context, String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        return !email.matches(emailPattern) || email.length() <= 0;

    }


    public static String getdate(String originalString) {
        String newstr = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
            newstr = new SimpleDateFormat("MM/dd/yyyy").format(date);
            System.out.println("\n" + newstr + "\n");
        } catch (ParseException e) {
            //Handle exception here
            e.printStackTrace();
        }
        return newstr;
    }



    public static String gettime(String originalString) {
        String newstr = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(originalString);
            newstr = new SimpleDateFormat("H:mm:ss aa").format(date);
            System.out.println("\n" + newstr + "\n");
        } catch (ParseException e) {
            //Handle exception here
            e.printStackTrace();
        }
        return newstr;
    }
    public static Long convertUTCToLocal(String dtStart) {

        Date date = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date.getTime();
    }

    public static String getmonth(String dtStart)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (String) DateFormat.format("MM",   date);
    }

    public static String getReadTimeMessage(String dtStart) {
        try {

            Long tim = convertUTCToLocal(dtStart);
            Long seconds, minutes, hours, days;
            String strTime = "--";

            Date currentTime = Calendar.getInstance().getTime();
            Log.e("DIFFREN ", tim + "___" + currentTime);
            long diffInMillisec = (currentTime.getTime()) - tim;
            long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);

            seconds = diffInSec % 60;
            diffInSec /= 60;
            minutes = diffInSec % 60;
            diffInSec /= 60;
            hours = diffInSec % 24;
            diffInSec /= 24;
            days = diffInSec;

            days = Math.abs(days);


            if (days == 0 && hours == 0 && minutes == 0 && seconds < 30) {
                strTime = "Just Now";
            } else if (days == 0) {
                SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:aa");
                Date now = new Date(tim);
                strTime = sdfTime.format(now);
            } else if (days == 1) {
                strTime = "Yesterday";
            } else if (days > 0 && days < 7) {
                SimpleDateFormat sdfTime = new SimpleDateFormat("EE");
                Date now = new Date(tim);
                strTime = sdfTime.format(now);

                switch (strTime.toUpperCase()) {
                    case "MON":
                        strTime = "Monday";
                        break;
                    case "TUE":
                        strTime = "Tuesday";
                        break;
                    case "WED":
                        strTime = "Wednesday";
                        break;
                    case "FRI":
                        strTime = "Friday";
                        break;
                    case "SAT":
                        strTime = "Saturday";
                        break;
                    case "SUN":
                        strTime = "Sunday";
                        break;
                }
            } else {
               /* SimpleDateFormat sdfTime = new SimpleDateFormat("dd-MM-yyyy");
                Date now = new Date(tim);
                strTime = sdfTime.format(now);*/

                int month = (int) (days / 31);
                if (days == 0 && hours == 0 && minutes == 0 && seconds < 30) {
                    strTime = "Just Now";
                } else if (days > 365) {
                    int yers = ((int) (days / 365));
                    if (yers > 1) {
                        strTime = yers + " Years ago";
                    } else {
                        strTime = yers + " Year ago";
                    }

                } else if (days > 0) {
                    if (days > 29) {
                        if(month==1)
                        {
                            strTime = month + " Month ago";
                        }
                        else
                        {
                            strTime = month + " Months ago";
                        }

                    } else if (days == 1) {
                        strTime = days + " Day ago";
                    }
                    else
                    {
                        strTime = days + " Days ago";
                    }

                } else if (hours > 0) {
                    strTime = hours + " Hours ago";
                } else {
                    if (minutes > 0) {
                        strTime = minutes + " Minute ago";

                    } else {
                        strTime = "Just Now";
                    }

                }


            }


            Log.e("getReadTimeMessage Day ", days + "\n hours" + hours + "\n minutes " + minutes + "\n strTime " + strTime);

            return strTime;
        } catch (Exception e) {
            Log.e("Exception : ", e.getMessage());
            return "--";
        }

    }

    public static String getReadTimeMessageday(String dtStart) {
        try {

            Long tim = convertUTCToLocal(dtStart);
            Long seconds, minutes, hours, days;
            String strTime = "--";

            Date currentTime = Calendar.getInstance().getTime();
            Log.e("DIFFREN ", tim + "___" + currentTime);
            long diffInMillisec = (currentTime.getTime()) - tim;
            long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);

            seconds = diffInSec % 60;
            diffInSec /= 60;
            minutes = diffInSec % 60;
            diffInSec /= 60;
            hours = diffInSec % 24;
            diffInSec /= 24;
            days = diffInSec;

            days = Math.abs(days);


            if (days == 0 && hours == 0 && minutes == 0 && seconds < 30) {
                strTime = "Just Now";
            } else if (days == 0) {
                SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:aa");
                Date now = new Date(tim);
                strTime = sdfTime.format(now);
            } else if (days == 1) {
                strTime = "Yesterday";
            } else if (days > 0 && days < 7) {
                SimpleDateFormat sdfTime = new SimpleDateFormat("EE");
                Date now = new Date(tim);
                strTime = sdfTime.format(now);

                switch (strTime.toUpperCase()) {
                    case "MON":
                        strTime = "Monday";
                        break;
                    case "TUE":
                        strTime = "Tuesday";
                        break;
                    case "WED":
                        strTime = "Wednesday";
                        break;
                    case "FRI":
                        strTime = "Friday";
                        break;
                    case "SAT":
                        strTime = "Saturday";
                        break;
                    case "SUN":
                        strTime = "Sunday";
                        break;
                }
            } else {
                SimpleDateFormat sdfTime = new SimpleDateFormat("dd-MM-yyyy");
                Date now = new Date(tim);
                strTime = sdfTime.format(now);
            }


            Log.e("getReadTimeMessage Day ", days + "\n hours" + hours + "\n minutes " + minutes + "\n strTime " + strTime);

            return strTime;
        } catch (Exception e) {
            Log.e("Exception : ", e.getMessage());
            return "--";
        }

    }


    public static HashMap<String, String> getDataHashMap(JSONObject jsonData) throws JSONException {
        HashMap<String, String> getData = new HashMap<>();
        Iterator<String> keysItr = jsonData.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            getData.put(key, jsonData.getString(key));
        }
        return getData;
    }

    /*public static Bitmap createCustomMarker(Context context) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }*/

}
