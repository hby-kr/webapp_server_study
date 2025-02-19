package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/getCookies.do")
public class L24GetCookies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 클라이언트가 요청과 함께 보낸 쿠키 얻기
        Cookie[] cookies = req.getCookies();
        String id = null; // 받을 메모리 자리 만들어주고
        String pw = null;
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("email")) { // 이름이 email이면
                id = cookie.getValue(); // 그놈의 값을 받고.
            } else if (cookie.getName().equals("password")) { // 또는 이름이 password면
                pw = cookie.getValue(); // 그놈의 값을 받고.
            } // 그 밖에는 날려.
        }


        // 쿠키를 받았으니 그것을 출력하는데 써보기로 함
        // 출력해보자
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>쿠키 불러오기 </h1>");
        out.println("<p> email:" + id + "</p>");
        out.println("<p> pw:" + pw + "</p>");
    }
}
