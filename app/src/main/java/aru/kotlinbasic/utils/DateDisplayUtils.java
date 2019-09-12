package aru.kotlinbasic.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A suite of utilities for displaying {@link Calendar} and {@link Date}
 * objects.
 */
public final class DateDisplayUtils {

    private static final String MONTH_YEAR_DISPLAY_PATTERN = "MM/yyyy";

    private DateDisplayUtils() {
        // hide constructor
    }

    public static String formatMonthYear(int year, int monthOfYear) {
        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        SimpleDateFormat format = new SimpleDateFormat(
                MONTH_YEAR_DISPLAY_PATTERN, Locale.getDefault());
        return format.format(calendar.getTime());
    }
}