package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EmpCRUDList.do")
public class L07EmpCrudList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<h1> 사원 관리 페이지 </h1>");
        out.println("<hr>");
        out.println("<h2> 사원리스트 </h2>");
        out.println("<a href='./empSignup.html'> 사원등록 폼 </a>");
        out.println("</html>");


    }
}
