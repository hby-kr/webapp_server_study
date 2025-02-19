package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/M25setBannerCookie.do")
public class M25setBannerCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // form 태그에서 매개변수 받아오기
        String isBannerCookie = req.getParameter("isBannerCookie");

        // 쿠키 생성 후 전송
        Cookie cookie = new Cookie("isBannerCookie", isBannerCookie);
        cookie.setMaxAge(60*10); // 10분 안보여주기
        resp.addCookie(cookie); // 쿠키 전송

        // 자동 꺼짐 만들어주기
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<script> setTimeout(()=>{ window.close();},1000)</script>");
    }
}
