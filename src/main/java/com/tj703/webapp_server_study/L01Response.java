package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// .do와 같은 확장자는 서블릿 요청과 일반 웹 페이지 요청을 구분하는 데 유용.
// .html 파일이나 .jsp 파일은 정적 리소스로 처리되고, (관례상) .do는 서블릿으로 처리된다는 구분을 할 수 있음
@WebServlet("/resStudy.do")
public class L01Response extends HttpServlet { // 동적리소스가 되려면 꼭 HttpServlet를 상속받아야 한다.
    /*
    이 곳이 비어있으면 405오류

    클라이언트(브라우저)가 서버에 요청하는 방법은 둘. get / post
        1. GET : URL을 요청하는 방법(링크를 눌렀을때)
        2. POST : input 양식에서 submit을 눌렀을 때, post방식으로 하겠다고 설절해야 함
    */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8"); // 파싱 방법을 선언
        resp.getWriter().write("<html><body>");

        resp.setContentType("application/json"); // 파싱 방법을 선언

        resp.getWriter().write("{\"id\" : \"abcdefg\"}");



    }
}
