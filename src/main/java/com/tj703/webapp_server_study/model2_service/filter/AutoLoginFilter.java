package com.tj703.webapp_server_study.model2_service.filter;

import com.tj703.webapp_server_study.model2_service.dto.LoginLogDto;
import com.tj703.webapp_server_study.model2_service.dto.UserDto;
import com.tj703.webapp_server_study.model2_service.dto.UserServiceLoginDto;
import com.tj703.webapp_server_study.model2_service.service.UserServiceImp;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter ("/*")
public class AutoLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest; // 사용하려고 형변환 하기
        if (req.getSession().getAttribute("auto_login") != null) { // auto_login이 null이 아니면
            filterChain.doFilter(servletRequest, servletResponse);
            return;
            // 로그인 되어 있으면 자동로그인을 하지 않음.
        }

        Cookie[] cookies = req.getCookies(); // 쿠키 다가져와
        Cookie autoLogin = null; // 받을 필드(속성) 만들어놓고
        Cookie autoEmail = null;
        Cookie autoPwd = null;
        if (cookies != null) { // null로 비어있지 않으면,
            /*  만약 NullPointerException (NPE)**은 자바에서 가장 흔하게 발생하는 오류 중 하나입니다.
                이 오류는 null 값을 참조하려고 할 때 발생합니다.
                즉, 객체가 null일 때 그 객체의 메서드를 호출하거나 속성에 접근하려고 할 때 발생    */
            for (Cookie c : cookies) { // for문 돌려서
                switch (c.getName()) { // 쿠키 배열에서 이름과 일치하는 쿠키들만 각가 받음
                    case "auto_login": autoLogin = c; break;
                    case "auto_email": autoEmail = c; break;
                    case "auto_password": autoPwd = c; break;
                }
            }
        }

        // 쿠키로 autoLogin이 요청되었는지 확인해서, 요청했으면..
        if (autoLogin != null && autoLogin.getValue().equals("1")){

            // 자동 로그인 준비하기
            try {
                // dto만들 준비
                String ip = req.getRemoteAddr();  // ip받고
                String agent = req.getHeader("Sec-Ch-Ua").split(",")[1]; // 브라우저 정보 받고
                UserDto user = new UserDto(); // 회원 dto 만들어서
                user.setEmail(autoEmail.getValue()); // email 받고
                user.setPassword(autoPwd.getValue()); // pw해쉬값 받고
                LoginLogDto loginLog = new LoginLogDto(); // 로그 dto 만들어서
                loginLog.setIpAddress(ip); // ip 받고
                loginLog.setUserAgent(agent); // 브라우저 정보 받고
                UserServiceLoginDto loginDto = new UserServiceImp().autoLogin(user, loginLog);  // UserServiceLoginDto 만들어서, 위 dto 두개 매개변수 넣어서 바로 메서드 구동

                if (loginDto != null && loginDto.getUser() != null) {  //로그인 성공 했으면
                    HttpSession session = req.getSession();
                    session.setAttribute("loginUser", loginDto.getUser()); // 세션에 로그인 유저 정보 넣고

                    if (!loginDto.isPwHistory()) { // 패스워드 6개월 어쩌구 작업하고..
                        HttpServletResponse resp = (HttpServletResponse) servletResponse;
                        resp.sendRedirect(req.getContextPath() + "/service/pwModify.do");
                        return;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse); // 그리고 나서 서블릿으로 간다.
    }
}
