package com.tj703.webapp_server_study.model2_service.controller;

import com.tj703.webapp_server_study.model2_service.dto.LoginLogDto;
import com.tj703.webapp_server_study.model2_service.dto.UserDto;
import com.tj703.webapp_server_study.model2_service.dto.UserServiceLoginDto;
import com.tj703.webapp_server_study.model2_service.service.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Map;

@WebServlet("/service/login.do")
public class UserLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 접속요청에 단순히 포워드만
        req.getRequestDispatcher("/WEB-INF/views/service/login.jsp").forward(req, resp);
    }

/*
UserServiceImp의 메서드를 Map으로 반환하게끔 설계해서 아래의 작업을 했다가
UserServiceImp에서 다시 메서드를 오버로딩하여 다른 작업으로 진행함.

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //email=inserttest%40gmail.com&password=1111&auto_email=1&auto_login=1
        //        email: inserttest@gmail.com
        //        password: 1111
        //        auto_email: 1
        //        auto_login: 1

        // 1. form에서 넘어온 post
        // 2. db와의 접속해서 회원인지 확인
        // 3. 회원이면, 서버 내 세션 객체에 회원정보dto를 저장
        // 4. 존재하지 않으면, 로그인 폼으로 돌아가, 로그인 실패 이유를 안내
        // 5. 오류나면? (로그인 중 오류가 발생했으니 다시 시도로 하라고 유도)


        // 1. form에서 넘어온 post
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("auto_login");
        String autoemail = req.getParameter("auto_email");

        // 2. db와의 접속해서 회원인지 확인
        //      필드 만들기
        String msg = null; // 세션에 msg를 보내서, view에서 msg가 있을 때, alert으로 경고 후 메세지를 삭제
        UserDto loginUser = null;
        boolean isPwHistory = false;
        //      ip와 클라이언트의 브라우저 조회하기
        String ipAddress = req.getRemoteAddr();
        String browser = req.getHeader("sec-ch-ua");
        String[] agents = browser.split(",");

        try {
            Map<String, Object> map = null;  // login 메서드가 Map을 반환하기로 설계해서 만드는 거임.
            map = new UserServiceImp().login(email, password, ipAddress, agents[1]); // 받은 정보 다 넣어서 로그인하고,
            // 회원정보와 정보이력있는지 여부, 로그가 들어갔는지 여부 등등 들어 있음
            // 그 중에서..
            Object loginUserObj = map.get("user"); // Object로 받는 이유는 Map 구조 중 값의 타입이 Object이기 때문.
            Object isPwHistoryObj = map.get("isPwHistory");
            // if null이면, 형변환 못함. 따라서 조건문 씀
            loginUser = (loginUserObj != null) ? (UserDto) loginUserObj : null;
            isPwHistory = (isPwHistoryObj != null) ? (Boolean) isPwHistoryObj : false;

        } catch (Exception e) {
            // throw new RuntimeException(e); 권장하지 않음. 오류 내용을 view화면 모두 띄우므로
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // 3. 회원이면, 서버 내 세션 객체에 회원정보dto를 저장 -> main으로 이동
        // 4. 존재하지 않으면, 로그인 폼으로 돌아가, 로그인 실패 이유를 안내
        // 5. 오류나면? (로그인 중 오류가 발생했으니 다시 시도로 하라고 유도)
        // 성공 실패 여부에 따라
        if (loginUser != null) {// login성공
            // 일단 세션은 만들자
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", loginUser); // 센션에 로그인 유저 정보 넣기

            // 그리고 비번 변경 요청
            if (isPwHistory) { // 6개월 이내에 비번변경 이력 있음 -> 6개월 이내에 바꿨음 -> 안바꿔도 됨
                resp.sendRedirect(req.getContextPath()+ "/");
            } else {  //이력이 없음  -> 6개월 더 되었음 -> 바꿔야 함
                resp.sendRedirect("./service/pwModify.do");
            }

        } else { //login 실패
            resp.sendRedirect("./login.do");
        }

    }
 */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. form에서 넘어온 post
        // 2. db와의 접속해서 회원인지 확인
        // 3. 회원이면, 서버 내 세션 객체에 회원정보dto를 저장
        // 4. 존재하지 않으면, 로그인 폼으로 돌아가, 로그인 실패 이유를 안내
        // 5. 오류나면? (로그인 중 오류가 발생했으니 다시 시도로 하라고 유도)

        // 1. form에서 넘어온 post
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("auto_login");
        String autoemail = req.getParameter("auto_email");
        // ip와 클라이언트의 브라우저 조회하기
        String ipAddress = req.getRemoteAddr();
        String browser = req.getHeader("sec-ch-ua");
        String[] agents = browser.split(",");


        // 2. db와의 접속해서 회원인지 확인

        //  사용할 필드부터 만들기
        String msg = null; // 세션에 msg를 보내서, view에서 msg가 있을 때, alert으로 경고 후 메세지를 삭제
        UserServiceLoginDto loginDto = null;

        // 매개변수 받아서 dto 생성하고 -> 그 dto를 넣어서 log메서드(db접속해서 확인하고 있는지 없는지 확인하는 메서드)돌리기
        try {
            // 로그인 form에서 받아온 변수로 회원dto, 로그dto 객체 만들기
            UserDto user = new UserDto(); // 회원 dto
            user.setEmail(email);
            user.setPassword(password);
            LoginLogDto log = new LoginLogDto(); // 로그 dto
            log.setIpAddress(ipAddress);
            log.setUserAgent(agents[1]);

            // 만든 dto를 매개변수로 login 서비스 돌리기
            loginDto = new UserServiceImp().login(user, log); // 로그인 실패면 null, 성공이면...
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        }

        // login 메서드의 성공,실패 여부로 리디렉션
        if (loginDto != null && loginDto.getUser() != null) { // 로그인 성공
            HttpSession session = req.getSession(); // 세션 만들어서
            session.setAttribute("loginUser", loginDto.getUser()); // 세션에 유저정보 값으로 넣기

            if(autoLogin != null && autoLogin.equals("1")) { // auto로그인도 사용자가 체크해서 매개변수로 들어왔다면
                // autoLogin이 있고, 1이면, 아래 코드로 쿠키 생성
                // 이 쿠키로 autoLoginFilter에서 자동로그인을 구현한다.
                Cookie autoPwCookie = new Cookie("auto_password", loginDto.getUser().getPassword());// 쿠키를 만들고
                Cookie autoLoginCookie = new Cookie("auto_login", autoLogin);
                Cookie autoEmailCookie = new Cookie("auto_email", loginDto.getUser().getEmail());
                autoPwCookie.setPath("/"); // 쿠기를 어디서 작동할 것인지 정하고
                autoLoginCookie.setPath("/");
                autoEmailCookie.setPath( "/");
                autoPwCookie.setMaxAge(60 * 60 * 24 * 30); // 만료시간 정하고
                autoLoginCookie.setMaxAge(60 * 60 * 24 * 30);
                autoEmailCookie.setMaxAge(60 * 60 * 24 * 30);
                resp.addCookie(autoPwCookie); // 쿠키를 넣는다
                resp.addCookie(autoLoginCookie);
                resp.addCookie(autoEmailCookie);
            }

            if (loginDto.isPwHistory()) { // true == 있음 == 최근 변경함
                resp.sendRedirect(req.getContextPath() + "/");
            } else { // false == 없음 == 6개월 이전 변경함 -> 리디렉트
                resp.sendRedirect(req.getContextPath() + "/service/pwModify.do");
            }
        } else { // 로그인 실패
            resp.sendRedirect(req.getContextPath() + "/service/login.do");
        }

    }
}
