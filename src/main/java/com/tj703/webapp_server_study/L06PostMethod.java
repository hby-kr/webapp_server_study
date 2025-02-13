package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// 이 페이지는 로그인한 form태그(index.html에 구현)에서 input값을 받아오는 엑션 페이지.
// 액션 페이지, 액션리소스; 이 페이지를 보여주는 것이 아니라 "처리만" 하는 페이지다.
// 액션페이지는 파라미터 값을 받고, 처리를 한 뒤, 리다이렉트 시켜준다.(내부에서 처리만 함)
@WebServlet("/postStudy.do")
public class L06PostMethod extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        GET & POST 차이점, 요청 헤더(request header body) 정보   (면접질문 get과 post의 차이.?)

        1. GET  : 브라우저에서 link나 url로 서버에 리소스를 요청하는 행위. 요청의 목적이 조회
             ~~url~~?b_id=999   --입력-->  <경민 리액트 수업>이라는 책 페이지로 이동
             = get 방식에서 파라미터는 리소스와 "대응" => 사용자가 다른 사용자에게 공유할 수 있는 리소스 주소. ex)

        2. POST : 양식(form 태그)을 제출해서 동적 리소스를 요청하는 데, 양식의 데이터를 "파라미터"로 전달하는 행위.
         데이터를 (저장하려고) 전송하거나 서버의 데이터를 수정할 때 사용
            로그인 (id=경민, pw=123456) --입력-->  리소스와 대응되지 않음,
                  => 사용자가 다른 사용자에게 공유할 수 "없는" 리소스 주소

        3. 요청헤더 : 서버에게 리소스를 요청할 때, 요청하는 클라이언트의 관련내역과 요청하는 리소스에 대한 상세한 정보
            => 서버가 어떻게 응답할 것인지 판단하도록 돕는다.
            post 방식으로 통신하면, 파라미터(querystring)을 요청헤더에 포함시켜 보낸다.

         */


        // index.html에 form태그 구현
        // post로 받은 매개변수(파라미터)부터 먼저 처리
        String userID = req.getParameter("userId");
        String password = req.getParameter("userPw");

        // 받을 매개변수를 화면에 내용을 보여줄 수도 있지만.
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.println("<h1> 성공" +userID+"님 </h1>");


        //리다이렉스 할 거임

        // 1) 조건 검증
        boolean result = false;
        if(userID.length() > 3 && password.length() >3){
            // jdbc로 회원가입 시도하고
            result = true;
        }
        // 2) 리다이렉트
        if (result) {
            resp.sendRedirect("./signupSuccess.html"); // 응답 코드 (http status) 302
        } else {
            resp.sendRedirect("./signupFail.html");
        }

    }
}
