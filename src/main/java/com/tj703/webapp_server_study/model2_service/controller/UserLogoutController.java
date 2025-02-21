package com.tj703.webapp_server_study.model2_service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet ("/service/logout.do")
public class UserLogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그아웃이란, session에 저장된 loginUser 객체가 null일 때.
        HttpSession session = req.getSession();
        session.removeAttribute("loginUser"); // 세션 내에 loginUser라는 키와 그 값을 모두 삭제
        session.invalidate(); // 다수의 객체로 로그인할 때는 session(안에 있는 것을 한번에) 완전히 만료할 수도 있음.

        // 로그아웃 눌렀을 때, 자동로그인 쿠키 없애기
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("auto_login") || c.getName().equals("auto_email") || c.getName().equals("auto_password"))  {
                    c.setMaxAge(0);
                    c.setPath("/");
                    resp.addCookie(c);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
