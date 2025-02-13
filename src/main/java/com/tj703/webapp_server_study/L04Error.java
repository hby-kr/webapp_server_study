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
/*
클라이언트에게 개발자나 서버가 요청실수를 알려주는 코드 400
    400 (Bad Request): 요청이 잘못 들어와 서버가 처리할 수 없음. 보내야할 매개변수가 없을 때 발생시키는 오류
    401 (Unauthorized): 인증이 못받음
    403 (Forbidden): 요청은 이해되었으나, 서버가 이를 처리할 권한이 없음.
    404 (Not Found): 요청한 자원을 찾을 수 없음. 클라이언트가 잘못된 url로 서버에 요청했을 때
    405 (Method Not Allowed): 잘못된 메서드로 요청했을 때.  doGet으로 만들었는데, post로 요청했을 때
서버가 잘못되면 상태번호 500 (개발자 실수)
    500 (Internal Server Error): 서버 내부 오류로 요청을 처리할 수 없음.
*/

        String bookIdStr = req.getParameter("book_id"); // 쿼리스트릥 특정 파라미터(bookId)를 가져오기

//        ex. 400 매개변수 오류.
//        (id는 숫자로만 설정 되어있는 상태로 가정)
//        book/detail.do?book_id=13  / 정상
//        book/detail.do?book_id=n13 / 잘못된 값
//        book/detail.do?book_id=    / "  "
//        book/detail.do?           / null

        try {
            // (id는 숫자로만 되어있는 상태로 가정)
            int bookId = Integer.parseInt(bookIdStr); // 숫자로 캐스팅
            // ID가 꼭 필요한데 보내지 않았거나, 잘못된 값을 보내면 페이지가 동작할 수 없으므로 => 400
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<h1>북 아이디가 " + bookId + "인 책을 불러오겠습니다. </h1>");

        } catch (NumberFormatException e) {  // NumberFormatException로 오류를 잡음
            e.printStackTrace(); // 예외를 콘솔에 뜨도록 설정하고

            // 선택할 수 있는 옵션은
            // 1) 400보내기
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // == resp.setStatus(400);
            // 2) 기존 400 페이지를 수정하기
            // 3) 오류페이지로 아예 이동시키기; 리다이렉트
            return;
        }

    }
}
