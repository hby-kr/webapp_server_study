package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/jdbcDeptList.do")
public class L05JdbcDeptList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";
        String sql = "select * from departments";

        List<Map<String, Object>> deptList = null;

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password); // 접속
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            deptList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("dept_no", rs.getString("dept_no"));
                map.put("dept_name", rs.getString("dept_name"));

                deptList.add(map);
            }


        } catch (Exception e) {
//             throw new RuntimeException(e); // throw하면 catch가 종료된다.
            // 얘보다
            resp.sendError(500);
            return;
            // 위방식을 추천.
            // 서버의 중요한 내역을 에러로 배포하면 해킹의 위험이 있다.
        }



        resp.setContentType("text/html; charset=utf-8"); // 타입 설정
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<h1> jdbc 부서 리스트</h1>");

        out.print("<table><tr><th>부서번호</th><th>이름</th></tr>");
        for (Map<String, Object> map : deptList) {
            out.print("<tr>" +
                    "<td>" + map.get("dept_no") + "</td>" +
                    "<td>" + map.get("dept_name") + "</td>");
            out.print("<td><a href:./deptDetail.do?dept_no="+ map.get("detp_no")+">상세</a></td>");
        }

        out.print("</tr>");
        out.print("</table>");

        out.print("</html>");

    }
}
/*

정적 리소스와 동적리소스
- 동적 리소스는 서버에서 실시간으로 생성되는 콘텐츠이고, 정적 리소스는 변경되지 않으며 클라이언트에 고정적으로 제공되는 콘텐츠

- 정적 리소스 (Static Resources)
    정적 리소스는 서버가 클라이언트에게 변경되지 않는 콘텐츠
    예를 들어, HTML 파일, CSS 파일, JavaScript 파일, 이미지(JPG, PNG 등) 등

- 동적 리소스 (Dynamic Resources)
    동적 리소스는 서버에서 실행된 코드나 데이터를 기반으로 실시간으로 생성되는 콘텐츠
    클라이언트의 요청에 따라 데이터를 처리하거나, 데이터베이스와 연동하여 최종적으로 HTML, JSON, XML 등의 형식으로 결과를 반환
    그때에 서버에서 사용하는 도구가 서블릿, JSP, 템플릿 엔진(Thymeleaf)


ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
 model 1 : 동적 페이지에서 접속하고 출력하는 모든 코드가 모여있는 디자인
          모든 비즈니스 로직과 뷰(출력) 코드가 한 곳에 모여 있는 디자인 패턴

 model 2 :  MVC (Model-View-Controller) 패턴  / Model = dao , View = html, Controller = Servlet
      Model (모델): =dao / 데이터를 가져오거나 데이터를 저장하는 역할
      View (뷰): =html / 사용자에게 출력을 제공하는 컴포넌트. 모델로부터 데이터를 받아서 사용자에게 보여줌.
      Model (모델): =Servlet / 사용자의 요청을 처리하고, 필요한 모델과 뷰를 연결하는 역할

MVC 패턴에서의 역할 분리:
    Model (모델): Java 파일, 특히 서블릿이나 서비스 클래스가 데이터 처리 및 비즈니스 로직을 담당
    View (뷰): JSP 파일은 UI를 담당하여 데이터를 출력하고, 사용자에게 보여주는 웹 페이지를 렌더링합니다.
    Controller (컨트롤러): Java 서블릿이나 Spring Controller와 같은 Java 파일이 요청을 처리하고, 적절한 JSP 파일을 호출하여 데이터를 전달합니다.


1. Model
모델에 해당하는 주요 코드 파일은 크게 둘,
서비스 클래스(Business Service)와 DAO(Data Access Object)입니다.
- 서비스 클래스는 비즈니스 로직을 처리하고, 실제 업무 흐름을 관리하는 역할
- DAO는 데이터베이스와의 연결을 담당하며, 데이터를 조회, 삽입, 수정, 삭제하는 작업을 처리

    1) 서비스 클래스
        Java 파일 안에 있는 클래스는 보통 객체 지향 프로그래밍(OOP) 관점에서 비즈니스 로직을 처리합니다.
        예를 들어, 데이터 처리, 서비스 로직, 도메인 모델 등을 담당하는 클래스들이죠.
        이러한 클래스는 Servlet과는 직접적인 관계 없이 독립적으로 존재할 수 있습니다.
    2) DAO ; DB접속


ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
2. View
    -  JSP(JavaServer Pages) 또는 HTML (동적/정적 웹 페이지)   + CSS(스타일시트),JavaScript (클라이언트 측 로직)
    -  템플릿 엔진 파일 (예: Thymeleaf, FreeMarker)

? JSP와 html
    1) JSP (JavaServer Pages):
        동적 웹 페이지를 생성하는 데 사용되는 템플릿 파일입니다.
        HTML과 Java 코드가 결합된 형태로,
        서블릿에서 전달된 데이터를 출력하는 데 사용
        JSP는 서버 측에서 처리되고, 클라이언트에게 HTML을 전송하는 방식으로 동작
    1-2) JSP외에 다른 템플릿 엔진 (Thymeleaf, FreeMarker 등):
        Thymeleaf는 Spring과 같은 프레임워크에서 자주 사용되는 템플릿 엔진
        JSP는 Java 코드가 직접 HTML 내에 포함되어 있어 비즈니스 로직과 뷰가 결합되는 경향이 있지만,
        템플릿 엔진 (예: Thymeleaf)은 HTML 템플릿을 비즈니스 로직과 분리할 수 있어, 뷰의 책임을 더 명확히 분리할 수 있습니다.

    2) HTML
        정적 웹 페이지로, JSP와 달리 서버에서 동적으로 처리되지 않고 클라이언트에게 그대로 전달되는 파일
        HTML 파일은 일반적으로 프론트엔드 작업에 사용되며,
        자바스크립트, CSS와 결합되어 페이지의 외형을 꾸미고 사용자 인터페이스를 구성
    2-2) JavaScript (클라이언트 측 로직)
        클라이언트 측에서 동적 기능을 구현하는 데 사용
        사용자와의 상호작용을 처리하거나,
        페이지를 새로고침하지 않고 데이터를 동적으로 처리하는 데 사용


ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
3. Controller
- Client의 요청을 받아 Client가 보낸 Data를 읽고 검사한다.
- Model에게 Business Logic을 요청한다.
- Model의 처리 결과는 맞는 View에게 응답을 요청한다.

사용자가 요청을 보내면, 컨트롤러는 모델을 호출하여 필요한 데이터를 처리하고, 그 결과를 동적으로 생성된 뷰에 전달합니다.
정적 리소스를 제공하는 경우, 컨트롤러는 정적 리소스에 대한 경로를 반환하여 클라이언트가 이를 요청할 수 있도록 합니다.

Servlet은 Java에서 컨트롤러(MVC 중 Controller) 역할을 하는 도구 중 하나.
(그 밖에 PHP 프레임워크인 Laravel이나 Symfony에서는 컨트롤러 클래스를 정의
Python에서는 Django나 Flask가, JavaScript의 Node.js와 Express 프레임워크에서는 라우터(router)가 있음)

서블릿은 웹 애플리케이션의 서버(WAS) 측에서 HTTP 요청(request)을 처리, 반환하는 Java 클래스 중 하나
서블릿은 웹 요청(HTTP Request)을 받아서 처리한 후 웹 응답(HTTP Response)을 보냅니다.

요청 처리: 클라이언트(사용자)가 보내는 HTTP 요청을 받아 처리합니다.
모델 호출: doGet()이나 doPost() 메서드를 통해 받은 HTTP 요청을
    비즈니스 로직을 처리하는 서비스 클래스나 DAO를 호출하여 데이터를 처리합니다.
응답 생성: 처리된 데이터를 바탕으로 동적 HTML을 생성하거나, 다른 포맷(JSON, XML 등)을 만들어 뷰로 데이터를 전달반환합니다.


ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
서블릿에서 doGet()이나 doPost()와 같은 메서드가
HttpServletRequest와 HttpServletResponse를 매개변수로 받는 이유는,
이 두 객체가 (클라이언트와 서버 간의 통신을 관리하는 데 필요한)
"요청" 정보와 "응답" 정보를 처리하는 역할을 하기 때문.


>> 두 객체의 메서드 알기
HttpServletRequest 는 요청과 관련된 메서드를
HttpServletResponse가 응답과 관련된 메서드를 구현해 가지고 있음

HttpServletRequest 객체가 제공하는 메서드를 이해하려면
클라이언트가 보내는 요청에 어떤 정보가 포함되는지 알아야.

주로 URL, 헤더, 파라미터, 본문 등으로 나눠집니다
    - 요청 URL (Request URL): HttpServletRequest.getRequestURI()로 이 URL 경로를 확인할 수 있습니다.

    - 쿼리 스트링 (Query String): URL의 끝에 ? 뒤에 오는 파라미터 부분
        클라이언트가 보낸 파라미터는 request.getParameter("id"), request.getParameter("name") 등의 메서드를 통해 접근할 수 있습니다.

    - 헤더 (Headers): HTTP 요청의 헤더는 메타데이터로 요청의 속성에 대한 정보를 포함
        예를 들어, User-Agent, Content-Type, Authorization 등의 헤더가 있습니다.
        request.getHeader("User-Agent")를 통해 클라이언트의 브라우저 정보를 받을 수 있습니다.

    - HTTP 메서드 (HTTP Method): GET, POST, PUT, DELETE 등
        request.getMethod()로 현재 요청에 사용된 HTTP 메서드를 확인할 수 있습니다.

    - 폼 데이터 (Form Data): POST 요청에서 클라이언트가 전송한 데이터. (웹 폼을 통해 사용자 입력을 받을 때)
        예를 들어, 로그인 폼에서 사용자가 입력한 username과 password는 POST 요청의 본문에 포함되어 서버로 전달됩니다.
        request.getParameter("username"), request.getParameter("password") 등을 통해 클라이언트가 입력한 값을 받아올 수 있습니다.

    - 세션 정보 (Session Data): 클라이언트와 서버 간의 세션 정보를 포함합니다.
        서버는 클라이언트가 로그인한 상태나 상태 정보를 세션을 통해 유지할 수 있습니다.

    - 쿠키 (Cookies):
        클라이언트가 서버와의 이전 통신에서 받은 쿠키 데이터를 요청 헤더에 포함시켜 보냅니다.
        이 쿠키는 클라이언트 상태를 추적하거나, 로그인 상태를 유지하는 데 사용됩니다.
        request.getCookies() 메서드를 통해 클라이언트가 보낸 쿠키들을 확인할 수 있습니다.

    - 본문 (Request Body): POST, PUT 요청에서 클라이언트가 보낸 본문 데이터입니다.
        예를 들어, JSON, XML, 또는 HTML 폼 데이터를 보낼 때 사용됩니다.
        request.getInputStream()을 사용하여 본문 데이터를 읽을 수 있습니다.


HttpServletRequest는 클라이언트가 보낸 요청에 대한 정보를 처리하는 객체
요청 파라미터, 헤더, 메서드, URI 등 다양한 정보를 다루는 메서드
주요 메서드:
*    getParameter(String name): 요청 파라미터를 가져옵니다.
    request.getParameter("id")는 요청에서 "id"라는 파라미터 값을 반환합니다.

    getHeader(String name): 요청 헤더 값을 가져옵니다.
    request.getHeader("User-Agent")는 클라이언트의 브라우저 정보를 반환합니다.

    getMethod(): HTTP 요청 방식(GET, POST 등)을 반환합니다.
    request.getMethod()는 "GET"이나 "POST"와 같은 값을 반환합니다.

    getRequestURI(): 요청된 URL의 URI 경로를 반환합니다.
    request.getRequestURI()는 /user/123과 같은 값을 반환할 수 있습니다.

    getSession(): 현재 세션 객체를 반환합니다.
    request.getSession()은 세션을 가져오거나, 세션이 없으면 새로 생성하여 반환합니다.

    getParameterMap(): 모든 요청 파라미터를 맵 형태로 반환합니다.
    request.getParameterMap()은 요청 파라미터들을 Map<String, String[]> 형태로 반환합니다.

    getAttribute(String name): 요청 속성 값을 반환합니다.
    request.getAttribute("user")는 요청에서 설정된 속성 값(예: user 객체)을 반환합니다.

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
HttpServletResponse 객체는
 서버가 클라이언트로 보낼 응답에 대한 다양한 정보를 다루는 객체
 상태 코드, 헤더 정보, 본문 내용 등을 설정

    - 상태 코드 (HTTP Status Code)
        200 OK: 요청이 성공적으로 처리되었습니다.
        301 Moved Permanently: 리소스가 영구적으로 이동되었습니다.
        302 Found: 리소스가 임시로 이동되었습니다.
        400 Bad Request: 클라이언트의 잘못된 요청입니다.
        404 Not Found: 요청한 리소스를 찾을 수 없습니다.
        500 Internal Server Error: 서버에서 오류가 발생했습니다.

    - 응답 헤더 (Response Headers)
    응답 헤더는 클라이언트가 응답을 어떻게 처리해야 할지에 대한 메타데이터를 제공
    예를 들어, 콘텐츠 타입, 캐시 제어, 쿠키 설정, 컨텐트 길이 등의 정보
        Content-Type: 응답 데이터의 MIME 타입을 설정합니다. 예: text/html, application/json 등.
        Cache-Control: 캐시 정책을 설정합니다. 예: no-cache, max-age=3600.
        Location: 리다이렉션을 위한 URL을 설정합니다.
        Set-Cookie: 클라이언트에 쿠키를 설정합니다.
        Content-Length: 응답 본문의 길이를 설정합니다.

    - 응답 본문 (Response Body)
    응답 본문은 클라이언트에게 실제로 전달할 데이터
    이 데이터는 HTML, JSON, XML, 이미지 파일 등 다양한 형태
        HTML: 웹 페이지를 구성하는 HTML 내용
        JSON: API 응답으로 자주 사용되는 JSON 형식의 데이터
        XML: 데이터 교환 형식으로 사용되는 XML 형식
        파일: 이미지, PDF 등 파일 데이터를 응답 본문에 포함

    - 쿠키 (Cookies)
    서버에서 클라이언트에게 쿠키를 설정하는 데 사용되는 메서드입니다.
    쿠키는 클라이언트의 상태를 추적하거나, 인증 정보를 저장하는 데 유용

    - 리다이렉션 (Redirection)
    서버가 클라이언트를 다른 URL로 리다이렉트할 때 사용되는 메서드


주요 메서드:
*    setStatus(int sc): 응답 상태 코드를 설정합니다.
            response.setStatus(200)은 HTTP 응답 코드 200 (성공)을 설정합니다.

*    setContentType(String type): 응답 콘텐츠의 MIME 타입을 설정합니다.
        response.setContentType("text/html")은 응답이 HTML 형식임을 지정합니다.

*    getWriter(): 응답 본문을 위한 PrintWriter 객체를 반환합니다.
        PrintWriter out = response.getWriter();는 클라이언트에게 응답을 보내기 위한 출력 스트림을 얻습니다.

*    sendRedirect(String location): 클라이언트를 다른 URL로 리다이렉트합니다.
        response.sendRedirect("/login")은 클라이언트를 /login 페이지로 리다이렉트합니다.

    setHeader(String name, String value): 응답 헤더를 설정합니다.
        response.setHeader("Cache-Control", "no-cache")은 응답 헤더에 캐시 방지 정보를 설정합니다.

    addCookie(Cookie cookie): 클라이언트에게 쿠키를 추가합니다.
        response.addCookie(new Cookie("sessionId", "12345"))는 클라이언트에게 쿠키를 전달합니다.

    getOutputStream(): 응답 본문을 위한 ServletOutputStream 객체를 반환합니다.
        response.getOutputStream()을 사용해 이진 데이터를 보낼 수 있습니다 (예: 이미지 파일).

    flushBuffer(): 응답 버퍼를 강제로 전송합니다.
        response.flushBuffer()는 서버에서 데이터를 즉시 클라이언트에게 전송하도록 합니다.

 */