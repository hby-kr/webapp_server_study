package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet ("/M25logout.do")
public class M25LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        //session.invalidate(); //전체 만료
        session.removeAttribute("userDto"); // 사용자정보 dto 객체만 삭제
        resp.sendRedirect(req.getContextPath() + "/M25CookieAndSession.jsp");
    }
}
