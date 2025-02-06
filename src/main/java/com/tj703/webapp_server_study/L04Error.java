package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/errorStudy.do")
public class L04Error extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // 서버가 잘못되면 상태번호 500 (개발자 실수)

        // 1.
        // throw new ServletException(); // 에러를 발생해서 위임처리(나를 실행하는 곳에서 오류를 발생시켜라

        // 2. runtime Exception
        // int i = Integer.parseInt("삼십삼");  // runtime error -> 심각한 오류 (예외처리)

    // 클라이언트에게 개발자나 서버가 요청실수를 알려주는 코드 400
        // 405:  잘못된 메서드로 요청했을 때.  doGet으로 만들었는데, post로 요청했을 때
        // 404:  클라이언트가 잘못된 url로 서버에 요청했을 때
        // 400:  보내야할 매개변수가 없을 때 발생시키는 오류
        //    book/detail.do?book_id=13  / 정상
        // id는 숫자로만 되어있는 상태임
        //    book/detail.do?book_id=n13 / 잘못된 값
        //    book/detail.do?book_id=    / "  "
        //    book/detail.do?            / null

        String bookIdStr = req.getParameter("bookId"); // 쿼리스트릥 특정 파라미터(bookId)를 가져오기

        try {
            // id는 숫자로만 되어있는 상태임
            int bookId = Integer.parseInt(bookIdStr); // 숫자로 캐스팅
            // ID가 꼭 필요한데 보내지 않았거나, 잘못된 값을 보내면 페이지가 동작할 수 없으므로 => 400
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<h1>북 아이디가 "+bookId+"인 책을 불러오겠습니다. </h1>");

        } catch (NumberFormatException e) {  // NumberFormatException로 오류를 잡음
            e.printStackTrace();

            // 1) 400보내기
             resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            // == resp.setStatus(400);
            // 2) 기존 400 페이지를 수정하기
            // 3) 오류페이지로 아예 이동시키기
            return;
        }


    }
}
