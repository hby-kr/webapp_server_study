package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// .do와 같은 확장자는 서블릿 요청과 일반 웹 페이지 요청을 구분하는 데 유용
// .html 파일이나 .jsp 파일은 정적 리소스로 처리되고, .do는 서블릿으로 처리된다는 구분을 할 수 있습니다.
@WebServlet("/resStudy.do")
public class L01Response extends HttpServlet { // 동적리소스가 되려면 꼭 HttpServlet를 상속받아야 한다.
    // 비어있으면 405오류
    // 브라우저가 서버에 요청하는 방법. 1. get  / 2. post
    //  1. GET : URL을 요청하는 방법(링크를 눌렀을때)
    //  2. POST : input 양식에서 submit을 눌렀을 때, post방식으로 하겠다고 설절해야 함

 /*
        HttpServletRequest와 HttpServletResponse는 서블릿(Servlet)에서
        HTTP 요청과 HTTP 응답을 처리하는 데 사용하는 중요한 객체입니다.
        이들은 서블릿 컨테이너(예: Tomcat)가 제공하며, 각각 클라이언트의 요청을 받고, 서버의 응답을 처리하는 데 사용됩니다.

        HttpServletRequest는 클라이언트의 요청을 받아서, 서블릿이 필요한 정보를 처리할 수 있게 해주는 객체입니다.
        HttpServletResponse는 서블릿이 처리한 결과를 클라이언트에게 보내는 데 사용되는 객체입니다.

        HttpServletRequest, request : 요청정보(url, cookie, parameter, 쿼리,
        HttpServletResponse, reponse : 응답할 내역 (보통은 문자열. 문서화 시켜서 보냄)

        >> HttpServletRequest: 클라이언트의 요청을 다룬다.
                getParameter(): 요청 파라미터값 가져오기.
                    예시: request.getParameter("username") → username 파라미터 값을 가져옴.
                getRequestURL(): 요청한 전체 URL 확인.
                    예시: request.getRequestURL() → https://example.com/index.jsp
                getHeader(): 요청 헤더 값 가져오기.
                    예시: request.getHeader("User-Agent") → 클라이언트의 브라우저 정보.
                getSession(): 세션 객체 가져오기. 용자의 세션을 추적할 때 사용
                    예시: request.getSession() → 세션 정보 반환.
                getMethod() :  요청의 HTTP 메서드(GET, POST 등)를 반환
                    예시: request.getMethod() → GET 또는 POST

        >> HttpServletResponse: 서버의 응답을 다룬다.
                setStatus(): 응답 상태 코드 설정. (예: 200 OK, 404 Not Found 등
                    예시: response.setStatus(HttpServletResponse.SC_OK) → 상태 코드 200(성공).
                getWriter(): 응답 본문을 작성할 출력 스트림 반환.
                    예시: PrintWriter out = response.getWriter(); out.println("Hello, World!"); → 응답으로 "Hello, World!"를 보냄.
                setHeader(String name, String value): 응답 헤더에 이름과 값을 설정.  예: 캐시 제어나 쿠키 설정 등을 할 때 사용
                    예시: response.setHeader("Cache-Control", "no-cache") → 캐시를 사용하지 않도록 설정.
                sendRedirect(): 클라이언트를 다른 URL로 리다이렉트.
                    예시: response.sendRedirect("https://example.com") → example.com으로 리다이렉트.
                setContentType(): 응답 콘텐츠 타입 설정.  (예: text/html, application/json 등).
                    예시: response.setContentType("text/html") → HTML 콘텐츠로 응답 설정.
                addCookie(Cookie cookie): 응답에 쿠키를 추가
                    예시: response.addCookie(new Cookie("username", "John")) → username=John 쿠키 설정.


         >> 헤더 header
            헤더란, 요청과 응답의 "정보"를 담고 있는 메타데이터
            데이터 그 자체(HTML, 이미지 등)와는 다른 부가적인 정보를 전달

            요청 헤더: 클라이언트가 서버에 보내는 요청에 대한 정보
                User-Agent: 클라이언트가 사용하는 브라우저 정보 (예: Chrome, Firefox 등)
                Accept: 클라이언트가 받을 수 있는 콘텐츠 유형 (예: text/html, application/json 등)
                Authorization: 사용자 인증 정보 (예: 로그인 토큰)


            응답 헤더: 서버가 클라이언트에게 보내는 응답에 대한 정보
                Content-Type: 응답 내용의 타입 (예: text/html, application/json)
                Cache-Control: 응답을 캐시할지 말지 설정 (예: no-cache는 캐시하지 않음)
                Location: 리다이렉트 응답 시 이동할 URL (예: https://example.com)
                    Content-Type: text/html; charset=UTF-8
                    Cache-Control: no-cache
                    Location: https://www.example.com


         >> 세션 session
            세션은 사용자와 서버 간의 연결을 추적하기 유지하기 위한 상태 정보를 저장하는 메커니즘(방법)
            HTTP는 상태 비저장(stateless) 프로토콜이기 때문에, 각 요청은 독립적으로 처리됩니다.
            즉, 서버는 클라이언트가 누구인지, 이전에 어떤 요청을 했는지 기억하지 못합니다. 이를 해결하기 위해 세션을 사용합니다.

            서버가 사용자에 대한 정보를 저장하여 여러 요청에 걸쳐 상태를 유지할 수 있도록 합니다.
            예를 들어, 사용자가 로그인한 상태를 기억하거나, 쇼핑몰에서 장바구니에 담은 상품을 유지하는 데 사용됩니다.

            세션 동작 방식:
            1. 사용자가 웹사이트에 처음 접속하면, 서버는 고유한 세션 ID를 생성하여 클라이언트(사용자의 브라우저)에 전달합니다.
             이 세션 ID는 쿠키나 URL을 통해 전달될 수 있습니다.
            2. 사용자가 이후에 사이트를 다시 방문하면, 클라이언트는 이 세션 ID를 서버로 보내고,
             서버는 이를 통해 이전에 저장된 상태(예: 로그인 정보, 장바구니 내용 등)를 복원합니다.


          >> 배포deploy 란
             톰캣 서버에 어플리케이션을 추가하는 것을 배포라고 한다. 그때 형식이 WAR.
                 WAR는 **웹 애플리케이션 아카이브(Web Application Archive)**의 줄임말
                 간단히 말해, 웹 애플리케이션을 하나의 압축 파일로 묶은 형식입니다.
                 WAR 파일에는 웹 애플리케이션의 모든 파일이 포함됩니다.
                 예를 들어, JSP 파일, 서블릿 클래스 파일, HTML, CSS, JavaScript, 이미지, 설정 파일(web.xml) 등이 포함됩니다.
             WAR 파일을 톰캣 서버에 배포하면 톰캣이 이를 자동으로 풀어서 실행할 수 있습니다.

             - 수정후 재배포 redeploy
             정적 리소스(HTML, CSS, 이미지 파일 등)는 수정하면 이미 배포된 부분에서 그 부분만 수정할 수 있다. 서버에서 파일을 그대로 읽어 응답을 하기 때문에.
             동적 리소스(코드나 서블릿, JSP, 클래스 파일)는 실시간으로 변하는 리소스이므로,
                배포를 다시 해야 함. 해당 클래스가 다시 컴파일되어야 하므로. 그리고 기존의 자바 클래스가 이미 메모리에 로드되어 있기 때문에.

          >> Explode (Exploded WAR)
             Exploded WAR는 WAR 파일을 풀어놓은 형태를 의미합니다.
             즉, WAR 파일을 압축 풀어서 각 파일을 "폴더 형태"로 배포하는 방식입니다.

        */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // response.out (printWrite )
        resp.setContentType("text/html;charset=utf-8"); // 파싱 방법을 선언
        resp.getWriter().write("<html><body>");

        resp.setContentType("application/json"); // 파싱 방법을 선언
        resp.getWriter().write("{\"id\" : \"abcdefg\"}");



    }
}
