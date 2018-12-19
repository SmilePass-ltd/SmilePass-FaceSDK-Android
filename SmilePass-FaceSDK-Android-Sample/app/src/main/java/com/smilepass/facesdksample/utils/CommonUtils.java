package com.smilepass.facesdksample.utils;

/**
 * Created by Mohit Rajput on 10/6/18.
 * TODO: Insert javadoc information here
 */

public class CommonUtils {
    public static String convertToCapsWithUnderscore(String text) {
        return text.replaceAll(" ", "_").toUpperCase();
    }
}
