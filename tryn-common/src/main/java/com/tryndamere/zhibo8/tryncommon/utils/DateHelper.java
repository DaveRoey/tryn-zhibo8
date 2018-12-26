package com.tryndamere.zhibo8.tryncommon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dave on 2018/12/26
 * Describes
 */
public class DateHelper {
    private final static String DATE_TIME="yyyy-MM-dd HH:mm:ss";


    public static Date dateFormat(String source){
       SimpleDateFormat sdf= new SimpleDateFormat(DATE_TIME);
        Date parse;
        try {
            parse = sdf.parse(source);
        } catch (ParseException e) {
            throw new UnsupportedOperationException();
        }
        return parse;

    }
}
