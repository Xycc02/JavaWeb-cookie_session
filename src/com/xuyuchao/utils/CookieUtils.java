package com.xuyuchao.utils;

import javax.servlet.http.Cookie;

/**
 * @auther xuyuchao
 * @create 2022-04-10-14:23
 */
public class CookieUtils {
    /**
     * 查找名为name的Cookie
     * @param name
     * @param cookies
     * @return 找到返回Cookie对象 没找到返回null
     */
    public static Cookie findCookie(String name,Cookie[] cookies) {
        if(name == null || cookies == null || cookies.length == 0) {
            return null;
        }
        //查找Cookie
        Cookie iwantcookie = null;
        for(Cookie cookie : cookies) {
            if(name.equals(cookie.getName())) {
                iwantcookie = cookie;
                return iwantcookie;
            }
        }
        return null;
    }
}
