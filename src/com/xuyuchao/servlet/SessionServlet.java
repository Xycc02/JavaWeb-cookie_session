package com.xuyuchao.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Session是一个接口(HttpSession)
 *  Session是会话，它是用来维护一个客户端和服务器之间关联的一种技术
 *  每个客户端都有自己的一个Session会话
 *  Session会话中，我们经常用来保存用户登录之后的信息
 * @auther xuyuchao
 * @create 2022-04-10-22:26
 */
public class SessionServlet extends BaseServlet{
    /**
     * 创建获获取Session
     * request.getSession() => 第一次调用时：创建Session会话   之后调用时: 获取前面创建好的Session会话对象
     * request.isNew() => 判断Session是不是刚创建出来的
     * 每个Session都有自己的唯一id
     * request.getId()
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建和获取Session会话对象
        HttpSession session = req.getSession();
        //判断Session是否是刚创建的
        boolean aNew = session.isNew();
        //获取Session的id
        String id = session.getId();

        resp.getWriter().write("得到的Session，它的id是" + id + "<br>");
        resp.getWriter().write("得到的Session，它的是否是新创建的" + aNew + "<br>");
    }

    /**
     * 往Session域对象中存入数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("key1","value1");
        resp.getWriter().write("成功往Session中保存了数据!");
    }

    /**
     * 往Session域对象中取出数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute("key1");
        resp.getWriter().write("从Session中获取的域对象key1的值为" + attribute.toString());
    }

    /**
     * Session的默认存活时间
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //获取了Session的默认超时时长
        int maxInactiveInterval = session.getMaxInactiveInterval();
        resp.getWriter().write("Session的默认超时时长:" + maxInactiveInterval);
    }

    /**
     * 单独设置Session的超时时长
     * 两次请求的间隔时长
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setTimeOut3s(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int defaultTimeOut = session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(3);
        int updateTimeOut = session.getMaxInactiveInterval();
        resp.getWriter().write("Session的默认超时时长为:" + defaultTimeOut + "秒<br>");
        resp.getWriter().write("设置Session的超时时长为:" + updateTimeOut + "秒");
    }


    /**
     * Session立即销毁
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //使Session立即销毁
        session.invalidate();
        resp.getWriter().write("Session立即超时!");
    }
}
