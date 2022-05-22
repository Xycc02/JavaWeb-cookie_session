package com.xuyuchao.servlet;

import com.xuyuchao.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.interfaces.RSAKey;

/**
 * @auther xuyuchao
 * @create 2022-04-10-13:20
 */
public class CookieServlet extends BaseServlet{

    /**
     * Cookie的创建
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、创建cookie对象
        Cookie cookie = new Cookie("key3","value3");
        //2、通知客户端保存cookie
        resp.addCookie(cookie);
        resp.getWriter().write("Cookie创建成功!");
    }

    /**
     * Cookie的获取
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Cookie,以对象数组的形式返回
        Cookie[] cookies = req.getCookies();
        //遍历一下Cookie
        for(Cookie cookie : cookies) {
            resp.getWriter().write("Cookie[" + cookie.getName() +" : " + cookie.getValue() + "] <br>");
        }
        //查找指定的cookie
        Cookie cookie = CookieUtils.findCookie("key1", cookies);
        if(cookie != null) {
            resp.getWriter().write("找到了名为key1的cookie");
        }

    }

    /**
     * Cookie值的修改
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //方案一
        //1、创建一个要修改的同名Cookie对象，并在构造器中重新赋值
        Cookie cookie = new Cookie("key1", "newValue1");
        //2、调用resp.addCookie(cookie)
        resp.addCookie(cookie);
        resp.getWriter().write("key1的值已经修改!<br>");

        //方案二
        //1、查找到需要修改的Cookie对象
        Cookie ck = CookieUtils.findCookie("key3", req.getCookies());
        //2、调用ck.setValue()赋予新的值给Cookie
        if(cookie != null) {
            ck.setValue("newValue3");
            resp.getWriter().write("成功修改key3的值!");
        }else {
            resp.getWriter().write("未找到名为key3的Cookie，修改失败!");
        }
        //3、通知客户端保存Cookie
        resp.addCookie(ck);
    }

    /**
     * Cookie的默认存活时间
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        //设置存活时间
        //正数，表示在指定的秒数只后过期
        //负数，表示浏览器一关，Cookie就会被删除(默认值是-1)
        //零，表示马上删除Cookie
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);
    }

    /**
     * Cookie的立即删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookieUtils.findCookie("key3", req.getCookies());
        //设置存活时间
        //正数，表示在指定的秒数只后过期
        //负数，表示浏览器一关，Cookie就会被删除(默认值是-1)
        //零，表示马上删除Cookie
        if(cookie != null) {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            resp.getWriter().write("立即删除key3成功!");
        }else{
            resp.getWriter().write("未找到key3，删除失败!");
        }


    }

    /**
     * 设置正数时间，自定义过期时间
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("life3600", "life3600");
        //设置Cookie一小时后过期
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个一小时后过期的Cookie"+ cookie.getName()+ "<br>");
    }

    /**
     * 设置path
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("pathTest", "pathTest");
        //req.getContextPath()得到当前工程路径
        //设置path为   /工程路径/abc
        cookie.setPath(req.getContextPath() + "/abc");
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个带有工程路径的cookie");
    }

    /**
     * 免用户名登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if("xyc1133".equals(username) && "123456".equals(password)) {
            //登录成功
            Cookie cookie1 = new Cookie("username",username);
            Cookie cookie2 = new Cookie("password", password);
            cookie1.setMaxAge(60*60);
            resp.addCookie(cookie1);
            cookie2.setMaxAge(60*60);
            resp.addCookie(cookie2);
            System.out.println("登录成功!");
        }else{
            System.out.println("登陆失败!");
        }
    }
}
