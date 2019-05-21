package com.tryndamere.zhibo8.harvest.common;

/**
 * @Author Dave
 * @Date 2019/5/21
 * @Description
 */
public class RedisNameSpace {
    public static final String SEPARATOR = ":";
    public static final String PARENT_NAME_SPACE = "ZHIBO8";


    /**
     * 用户key namespace
     */
    public final static String USER_NAME_SPACE = PARENT_NAME_SPACE + SEPARATOR + "USER" + SEPARATOR;
}
