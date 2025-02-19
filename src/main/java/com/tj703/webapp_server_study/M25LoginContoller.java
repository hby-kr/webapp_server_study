package com.tj703.webapp_server_study;

import com.tj703.webapp_server_study.model2_service.dao.UserDao;
import com.tj703.webapp_server_study.model2_service.dao.UserDaoImp;
import com.tj703.webapp_server_study.model2_service.dao.UserManagerDBConn;
import com.tj703.webapp_server_study.model2_service.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/M25Login.do")
public class M25LoginContoller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // form에서 post로 받은 매개변수 처리
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // form에서 입력한 값으로 db에서 동일한 값 불러와야 함
        UserDto userDto = null; // dto 자리 만들어 놓고
        Connection conn = null;

        try {
            conn = UserManagerDBConn.getConnection();
            UserDao userDao = new UserDaoImp(conn);
            // UserDto paraDto = new UserDto();
            // paraDto.setEmail(email);
            // paraDto.setPassword(password);
            userDto = userDao.findByemailAndPassword(email, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 성공-실패 여부에 따라 조치 취하기
        String msg = "";
        HttpSession session = req.getSession();

        if (userDto != null) { // 성공 했으면
            msg = "로그인 성공"; //
            session.setAttribute("userDto", userDto); // 세션에 회원 정보 dto 객체를 그냥 넣을거임. 서버에만 있어서 안전
            session.setAttribute("msg", msg);
        } else { // 실패면
            msg = "번호와 이름을 다시 확인하세요";
            session.setAttribute("msg", msg);
        }

        System.out.println(msg);
        resp.sendRedirect(req.getContextPath() + "/M25CookieAndSession.jsp");


    }
}
