package com.open.common.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateTimeUtils {

    //joda-time
    /**
     * default date-time format
     */
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";


    //str->Date

    /**
     * string to date
     * @param dateTimeStr
     * @param formatStr string of date-time format
     * @return
     */
    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    //Date->str

    /**
     *
     * @param date
     * @param formatStr string of date-time format
     * @return
     */
    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    //str->Date

    /**
     *
     * @param dateTimeStr
     * @return
     */
    public static Date strToDate(String dateTimeStr) {
        return strToDate(dateTimeStr, STANDARD_FORMAT);
    }

    //Date->str

    /**
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, STANDARD_FORMAT);
    }

    /**
     *
     * @param timestamp
     * @return
     * @throws ParseException
     */
    public static Date timestampToDate(Long timestamp) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_FORMAT);
        String dateString = format.format(timestamp);
        return format.parse(dateString);
    }

    /**
     *  Test if  date1 compare to date2
     * @param d1
     * @param d2
     * @return
     */
    public static int compare(Date d1, Date d2) {
        if (Objects.isNull(d1) || Objects.isNull(d2)) {
            throw new IllegalArgumentException();
        }
        return d1.compareTo(d2);
    }

    /**
     *  Test if date1 is before date2
     * @param d1
     * @param d2
     * @return
     */
    public static boolean before(Date d1, Date d2) {
        if (Objects.isNull(d1) || Objects.isNull(d2)) {
            throw new IllegalArgumentException();
        }
        return d1.before(d2);
    }

    /**
     * Test if date1 is after date2
     * @param d1
     * @param d2
     * @return
     */
    public static boolean after(Date d1, Date d2) {
        if (Objects.isNull(d1) || Objects.isNull(d2)) {
            throw new IllegalArgumentException();
        }
        return d1.after(d2);
    }
}
