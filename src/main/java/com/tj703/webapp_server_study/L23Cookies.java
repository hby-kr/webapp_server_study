package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// setCookies ; 쿠키를 만들고 보내서 브라우저(클라이언트)에 남기려는 페이지
// getCookies ; 쿠키를 받아서 작업하는 페이지
@WebServlet("/setCookies.do")
public class L23Cookies extends HttpServlet {

    // url을 통해 사용자가 접속을 요청한다. 그러면 서버는 그 요청을 받아서
    // 여러 가지 방식의 "응답"을 하는 것.
    // 페이지의 컨텐츠를 보여주는 것도 하나의 요청의 하나의 방식이고, 리디렉션 또한 하나의 요청방식이다.
    // 여기서는 여러 요청 방식 중 하나로 쿠키를 보내는 것을 설명하는 것
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 쿠기 생성 - 제한설정 - 쿠키전송

        // (서버에서) 쿠키 만들기 (키-값 형식)
        // 쿠키의 실제 값은 Map<String, String> 형태
        Cookie cookie = new Cookie("email", "joeun@gmail.com");
        Cookie cookie2 = new Cookie("password", "123456");
        // cookie.setPath("/"); 쿠키가 유효할 경로를 지정. 그로써 특정 디렉토리나 URL 경로에서만 쿠키를 사용할 수 있도록 함
        // path를 설정하지 않으면, 기본적으로 요청한 페이지의 경로와 일치하는 경로에만 유효
        // default 경로 : "localhost:8080/contextPath/ * 이 경로에서 유효한 쿠키

        // 만료시간 설정
        // cookie.setMaxAge(0); // 0은 삭제
        cookie.setMaxAge(15*60); // 단위는 초, 3600초 = 1시간
        cookie2.setMaxAge(5*60);

        // 쿠키 같이 보내기
        resp.addCookie(cookie);
        resp.addCookie(cookie2);

        // 요청과 별개로 페이지 컨텐츠를 만들어 보내기
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1> 쿠키생성 email과 password </h1>");


    }
}
/*
>>> 쿠키Cookie와 세션Session

> 쿠키Cookie
    쿠키는 서버가 웹사이트를 통해 사용자의 컴퓨터에 저장하는 문자열된 데이터 조각
    브라우저가 서버에 요청할 때마다 요청정보를 보내면서 (사용자)컴퓨터에 남겨놓은 쿠키파일도 함께 전달.
    이를 통해 서버는 사용자가 이전에 해당 어플리케이션에서 어떤 작업을 했는지 알 수 있음
    보통 이름과 값이 쌍으로 저장 / 만료일 설정할 수 있음 / 사용자가 삭제 가능 / 일반적으로 4KB 이하로 제한
    ex) 로그인 정보, 사용자 설정(언어,테마 등) 저장
        사용자 ID: 로그인한 사용자를 구별할 수 있도록 고유한 ID를 저장
        웹사이트 설정: 사용자가 선택한 언어나 화면 설정
        장바구니 내용: 쇼핑몰에서 장바구니에 담은 상품 정보
        방문 기록: 이전에 방문한 페이지나 클릭한 상품 기록

>>> jakarta.servlet.http.Cookie;
쿠키의 이름과 값을 설정하고, 그 외에도 유효 기간, 경로, 도메인 등 쿠키의 다양한 속성을 관리하는 데 사용

Cookie 클래스 주요 메서드
    (get ; get 메서드는 값을 읽어오는 용도, )
        1. getName()    쿠키의 이름을 반환합니다.
        2. getValue()   쿠키의 값을 반환합니다.
        3. getMaxAge()  쿠키의 유효 기간(초)을 반환합니다.
        4. getPath()    쿠키의 유효 경로를 반환합니다.
        5. getDomain()  쿠키의 유효 도메인을 반환합니다.
    (set ; set 메서드는 값을 설정하는 용도)
        6. setName(String name)      쿠키의 이름을 설정합니다.
        7. setValue(String value)    쿠키의 값을 설정합니다.
        8. setMaxAge(int expiry)     쿠키의 유효 기간을 설정합니다 (초 단위).
        9. setPath(String uri)       쿠키가 유효한 경로를 설정합니다.
        10. setDomain(String domain) 쿠키가 유효한 도메인을 설정합니다.
        11. setSecure(boolean flag)     true로 설정하면 쿠키가 HTTPS 연결에서만 전송됩니다.
        12. setHttpOnly(boolean flag)   true로 설정하면 JavaScript에서 쿠키에 접근할 수 없게 설정합니다.

(Cookie 클래스 외) 자주 사용하는 Cookie 관련 메서드
> Request에서 쿠키 관련 메서드
    HttpServletRequest 객체는 클라이언트로부터 전송된 쿠키 정보를 읽어올 때 사용됩니다.
    getCookies()    ; 클라이언트로부터 전송된 모든 쿠키를 배열로 반환합니다.

> Response에서 쿠키 관련 메서드
    esponse 객체는 쿠키를 클라이언트에게 설정하는 데 사용됩니다.
    addCookie(Cookie cookie)    ; 응답에 쿠키를 추가합니다. 클라이언트에게 전송될 쿠키를 설정
 */