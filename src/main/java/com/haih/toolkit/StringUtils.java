/* 
 * 
 * Copyright (C) 1999-2012 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：StringUtils.java						
 * 
 * Description：	
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0       admin       2015年8月21日下午5:05:20         Create
 */
package com.haih.toolkit;

import java.io.UnsupportedEncodingException;

import com.google.common.base.CharMatcher;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author haihu
 * @version 2015-08-21
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    

    /**
     * @descrption 除去字符串前后多余的空格 
     * @author haihu
     * @create 2015年8月21日下午5:09:52
     * @version 1.0
     */
    public static String trimSpace(String source){
        return CharMatcher.BREAKING_WHITESPACE.trimFrom(source);
    }
    
    /**
     * @descrption 将字符串中多个空格合并为一个
     * @author haihu
     * @create 2015年8月21日下午5:21:41
     * @version 1.0
     * @param source
     * @return
     */
    public static String trimCollapse(String source){
        return CharMatcher.WHITESPACE.trimAndCollapseFrom(source,' ');
        
    }
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
        if (str != null){
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }else{
            return null;
        }
    }
    
    /**
     * 字节数组转换为字符串
     * @param str
     * @return
     */
    public static String toString(byte[] bytes){
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }
    
    /**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs){
        if (str != null){
            for (String s : strs){
                if (str.equals(trim(s))){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val){
        if (val == null){
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val){
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val){
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val){
        return toLong(val).intValue();
    }
    

    /**
     * 驼峰命名法工具
     * @return
     *      toCamelCase("hello_world") == "helloWorld" 
     *      toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *      toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     * @return
     *      toCamelCase("hello_world") == "helloWorld" 
     *      toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *      toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    /**
     * 驼峰命名法工具
     * @return
     *      toCamelCase("hello_world") == "helloWorld" 
     *      toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *      toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }
    
    /**
     * 如果不为空，则设置值
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
        if (isNotBlank(source)){
            target = source;
        }
    }

}

