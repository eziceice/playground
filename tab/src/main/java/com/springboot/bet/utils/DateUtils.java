package com.springboot.bet.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Date Utils
 */
public class DateUtils {

    /**
     * Get calculated date from input hours
     * Positive hours - future date
     * Negative hours - previous date
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date getCalculatedDateFromHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
