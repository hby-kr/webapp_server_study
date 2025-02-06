package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/postStudy.do")
// 이 페이지는 로그인한 form태그에서 input값을 받아오는 페이지
// 액션 페이지, 액션리소스; 이 페이지를 보여주는 것이 아니라 처리만 하는 페이지다.
// 액션페이지는 파라미터 값을 받고, 처리를 한 뒤, 리다이렉트 시켜준다.
public class L06PostMethod extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 양식을 다시 제출하는 것을 확인하는 이유?
        // -> post로 통신한 페이지는 url에 파라미터가 없고, 파라미터와 리소스가 대응되지 않기 때문에,
        //    새로고쳤을 때 원하는 리소스를 받을 수 없다고 경고를 보내는 것

        // GET  : book 상세페이지 ?b_id=999   --입력-->  <경민 리액트 수업>이라는 책 페이지로 이동
        //         = get 방식에서 파라미터는 리소스와 "대응" => 사용자가 다른 사용자에게 공유할 수 있는 리소스 주소. ex)
        // POST : 로그인 (id=경민, pw=123456) --입력-->  리소스와 대응되지 않음,
        //          => 사용자가 다른 사용자에게 공유할 수 "없는" 리소스 주소
        // 면접질문 get과 post의 차이.

        String userID = req.getParameter("userId");
        String password = req.getParameter("userPw");

        // 얘는 화면에 내용을 보여주는 것
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.println("<h1> 성공" +userID+"님 </h1>");

//        리다이렉스 할 거임

        // 조건 검증
        boolean result = false;
        if(userID.length() > 3 && password.length() >3){
            // jdbc로 회원가입 시도하고
            result = true;
        }
        // 리다이렉트
        if (result) {
            resp.sendRedirect("./signupSuccess.html"); // 응답 코드 (http status) 302
        } else {
            resp.sendRedirect("./signupFail.html");
        }




    }
}
