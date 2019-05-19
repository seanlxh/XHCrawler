package com.seanlxh.crawler.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static boolean matches(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private static final String URL_REGEX = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    public static boolean isUrl(String str) {
        if (str==null || str.trim().length()==0) {
            return false;
        }
        return matches(URL_REGEX, str);
    }


}
