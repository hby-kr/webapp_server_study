package com.tj703.webapp_server_study;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
/*
> 클라이언트 (화면 켬퓨터, 사용자의 컴퓨터)

> 서버란 무엇인가
   서버; 컴퓨터에 설치되어 있는 서버프로그램   ex. MySQL
   컴퓨터는 모두 web상에 고유의 ip(internet protocol) 주소를 가진다.
      * ip주소 인터넷에 연결된 모든 디바이스에 할당되는 고유 식별 번호

>  웹web 서버(Web Server)는 정적인 것들, 즉 바뀌지 않는 것들만 화면에 뿌려주는 것이고   (Apache, Nginx)

>  와스WAS(Web Application Server)는  실시간으로 사용자들이 원하는 데이터를 요청에 따라 반납 하는 것이다.
      (Tomcat, Node.js, spring)
      동적 리소스는 요청이 들어올 때마다 서버에서 생성되거나
      데이터베이스와 연동하여 실시간으로 변화된 결과를 사용자에게 제공합니다.
      요청받으면, WAS에서 실행하여, 그 실행 결과를 WS로 전달.

>  데이터베이스DB 서버   (MySQL)

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
*jar 파일명은 자바외 외부 압축버전.

서블릿(Servlet)은 Java를 이용한 웹 애플리케이션을 개발할 때 사용되는 서버 측 프로그램입니다.
    간단히 말해서, 서버에서 실행되는 자바 코드로, HTTP 요청을 받아서 응답reqeust을 응답response하는 역할을 합니다.
    서블릿은 웹 서버에서 HTTP 요청request을 처리하고, 그에 대한 HTTP 응답response을 생성하는 자바 클래스입니다.
    웹 서버(예: Tomcat)에서 서블릿 컨테이너(Servlet Container)가 서블릿을 실행하고 관리합니다.

서블릿이 하는 일:  요청->처리->응답
    1. 클라이언트 요청 처리:
        사용자가 웹 브라우저에서 요청을 보내면, 서블릿은 이 요청을 받아서 처리합니다.
    2. 로직 처리:
        서블릿은 요청을 처리하는 비즈니스 로직을 실행합니다.
        예를 들어, 데이터베이스에서 정보를 조회하거나, 입력된 데이터를 처리하는 등의 작업을 할 수 있습니다.
    3. 응답 생성:
        서블릿은 처리된 정보를 바탕으로 HTTP 응답을 생성해서 클라이언트에게 반환합니다.
        이 응답은 HTML, JSON, XML 등 다양한 형식일 수 있습니다.
 */

@WebServlet("/hello-servlet") // 꼭 주소 앞에 /를 써야한다. / 절대경로를 매핑하는 것
// @annotation ; 컴파일시 검사하거나 자동완성하는 것
// @WebServlet ; 컴파일시 해당 클래스의 동적리소스 주소를 등록하는 것
public class HelloServlet extends HttpServlet { // HttpServlet를 꼭 상속받아야 한다.

    private String message;

    public void init() {
        message = "이거 뭔데, 웹앱서버 톰캣임";
    } // 무조건 실행되는 메서드
    // 서블릿이 최초로 생성될 때 한 번 호출됩니다. 서블릿 초기화 작업을 수행하는 데 사용됩니다.
    // 용도: 데이터베이스 연결, 리소스 초기화 등의 작업을 수행할 수 있습니다.


    // url (/hello-servlet)로 해당 리소스를 요청하면, doGet()이 실행된다.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         doGet()은 HTTP GET 요청을 처리하는 메서드
         doGet()은 주로 읽기 작업에 사용됩니다. 예를 들어, HTML 페이지나 데이터를 클라이언트로 반환할 때 사용

         doGet의 매개변수를 둘이다. HttpServlet의 Request와 Response라는 두 객체
         물리세계의 요청을 받는 객체인 Request와, 다시 사용자에게 응답을 보내는 Response.
         HTTP 요청과 HTTP 응답을 처리하는 데 사용하는 중요한 객체.
         이들은 서블릿 컨테이너(예: Tomcat)가 제공하며, 각각 클라이언트의 요청과 응답을 처리하는 데 사용

         HttpServletRequest는
         클라이언트(사용자)로부터 서버로 전달된 HTTP 요청에 대한 정보를 제공

            > 주요 기능: (파라미터, URI, 헤더, 세션, 쿠기)
                getParameter(): 요청 파라미터값 가져오기.
                    예시: request.getParameter("username") → username 파라미터 값을 가져옴.
                getRequestURL(): 요청한 전체 URL 확인.
                    예시: request.getRequestURL() → https://example.com/index.jsp
                getContextPath(): 요청된 페이지가 속한 어플리케이션 루트를 불러옴 (개발시 사용)
                getHeader(): 요청 헤더 값 가져오기.
                    예시: request.getHeader("User-Agent") → 클라이언트의 브라우저 정보.
                getSession(): 세션 객체 가져오기. 용자의 세션을 추적할 때 사용
                    예시: request.getSession() → 세션 정보 반환.
                getMethod() :  요청의 HTTP 메서드(GET, POST 등)를 반환
                    예시: request.getMethod() → GET 또는 POST

         HttpServletResponse는
         (요청을 받아 처리한 후에) 서버가 클라이언트에게 다시 보낼 HTTP 응답을 처리

            > 주요 기능: (상태코드, 응답본문, 리다이렉션, 헤더설정, 쿠키추가)
                setStatus(): 응답 상태 코드 설정. (예: 200 OK, 404 Not Found 등
                    예시: response.setStatus(HttpServletResponse.SC_OK) → 상태 코드 200(성공).
                setContentType(): 응답 콘텐츠 타입 설정.  (예: text/html, application/json 등).
                    예시: response.setContentType("text/html") → HTML 콘텐츠로 응답 설정.
                getWriter(): 응답 본문을 작성할 출력 스트림 반환.
                    예시: PrintWriter out = response.getWriter(); out.println("Hello, World!"); → 응답으로 "Hello, World!"를 보냄.
                sendRedirect(): 클라이언트를 다른 URL로 리다이렉트.
                    예시: response.sendRedirect("https://example.com") → example.com으로 리다이렉트.

                setHeader(String name, String value): 응답 헤더에 이름과 값을 설정.  예: 캐시 제어나 쿠키 설정 등을 할 때 사용
                    예시: response.setHeader("Cache-Control", "no-cache") → 캐시를 사용하지 않도록 설정.
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

 */

        response.setContentType("text/html"); // html인데 text로 응답할거임. 형식지정
        PrintWriter out = response.getWriter(); // 화면에 뿌릴 거임.
        out.println("<html><br><hr><body>");
        out.println("<h1 style = 'color:blue;'>" + message + (11 * 11) + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() { // 끝이 나면 실행
        // 서블릿이 서비스를 종료할 때 호출됩니다. 서블릿이 메모리에서 제거되기 전에 리소스를 정리하는 데 사용
        // 열린 파일이나 데이터베이스 연결을 닫거나 정리 작업을 수행
    }
}

/*

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