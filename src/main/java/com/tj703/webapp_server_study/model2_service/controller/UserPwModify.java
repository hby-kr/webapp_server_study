package com.tj703.webapp_server_study.model2_service.controller;

import com.tj703.webapp_server_study.model2_service.dto.UserDto;
import com.tj703.webapp_server_study.model2_service.service.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.swing.text.html.HTML;
import java.io.IOException;

@WebServlet("/service/pwModify.do")
public class UserPwModify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/service/pwModify.jsp").forward(req, resp);
    } // get요청에는 포워드만.


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // form_post로 받은 매개변수 먼저 처리
        String pw = req.getParameter("pw");
        String pwConfirm = req.getParameter("pwConfirm");

        String msg = null;

        // 클라이언트_쿠키_세션에서 가져온
        HttpSession session = req.getSession();
        Object loginUserObj = session.getAttribute("loginUser");

        // 로그인 안되었으면 바꿀 수 없음
        if(loginUserObj == null) {
            msg = "로그인을 먼저 진행하세요";
            session.setAttribute("msg", msg);
            resp.sendRedirect(req.getContextPath() + "/service/login.do");
            return; // 여기서 끝남
        }

        // null 아니니까 형변환 가능
        UserDto loginUser = (UserDto) loginUserObj;
        boolean modify = false;

        // 비번 변경 시도
        try {
            loginUser.setPassword(pw); // 받은 loginUser에서 pw만 변경
            modify = new UserServiceImp().modifyPw(loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // 실패 여부에 따라 리디렉트
        String redireactUrl = req.getContextPath();
        if(modify) {
            msg = "비밀번호 변경 성공";
            redireactUrl += "/";
        } else {
            msg = "비밀번호 변경 실패. 이전에 사용한 비번입니다."; // 이전에 비밀번호를 썼을 경우, 형식이 안맞을 경우
            redireactUrl +="/service/pwModify.do";
        }
        session.setAttribute("msg", msg);
        resp.sendRedirect(redireactUrl);

    }
}
