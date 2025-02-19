package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/setBannerCookie.do")
public class L25SetBannerCookie extends HttpServlet {

    // form문으로 제출받은 요청 처리하기. get으로 보냈음.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get으로 보내진 쿼리스트링 값 받기
        // <input type="checkbox" name="isBannerCookie" value="1" checked />
        // form태그에 이거 하나있음. 그러니 이거 하나 받는 것
        String isBannerCookie = req.getParameter("isBannerCookie"); // 1 // == isBannerCookie라는 이름의 파라미터 값을 가져오겠다.

        // 쿠키 만들고, 만료시간 정하고, 보내기
        Cookie cookie = new Cookie("isBannerCookie", isBannerCookie); // 받아 온 값으로 쿠키만들고
        cookie.setMaxAge(60*5); // 만료시간 설정하고
        resp.addCookie(cookie); // 쿠키 보내기

        // 자동 꺼짐 작동하게 코드 짜기
        // 쿠키 만들어 놓고 PrintWriter로 script 만들어 실행하기
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<script> setTimeout(()=>{ window.close();},1000)</script>");
    }
}
