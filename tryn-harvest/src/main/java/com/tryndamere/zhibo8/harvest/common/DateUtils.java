package com.tryndamere.zhibo8.harvest.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author Dave
 * @Date 2019/5/16
 * @Description
 */
public class DateUtils {
    private final String YYYY_MM_DD = "yyyy_MM_dd";


    public static  String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static int  subDay(Date start,Date end){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(end.getTime()-start.getTime());
        return (calendar.get(Calendar.DATE)-1);
    }

}
