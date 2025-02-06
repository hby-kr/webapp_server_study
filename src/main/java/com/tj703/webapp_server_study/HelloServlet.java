package com.tj703.webapp_server_study;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
/*

서버란 무엇인가

   서버; 컴퓨터에 설치되어 있는 서버프로그램   ex. MySQL

   컴퓨터는 모두 web상에 고유의 ip(internet protocol) 주소를 가진다.
      * ip주소 인터넷에 연결된 모든 디바이스에 할당되는 고유 식별 번호

>  웹web 서버(Web Server)는 정적인 것들, 즉 바뀌지 않는 것들만 화면에 뿌려주는 것이고   (Apache, Nginx)

>  와스was(Web Application Server)는  실시간으로 사용자들이 원하는 데이터를 요청에 따라 반납 하는 것이다.
      (Tomcat, Node.js, spring)
      동적 리소스는 요청이 들어올 때마다 서버에서 생성되거나
      데이터베이스와 연동하여 실시간으로 변화된 결과를 사용자에게 제공합니다.

      요청받으면, WAS에서 실행하여, 그 실행 결과를 WS로 전달.

>  데이터베이스DB 서버   (MySQL)

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

*jar 파일명은 자바외 외부 압축버전.


서블릿(Servlet)은 Java를 이용한 웹 애플리케이션을 개발할 때 사용되는 서버 측 프로그램입니다.
    간단히 말해서, 서버에서 실행되는 자바 코드로, HTTP 요청을 받아서 응답을 반환하는 역할을 합니다.
    서블릿은 웹 서버에서 HTTP 요청을 처리하고, 그에 대한 HTTP 응답을 생성하는 자바 클래스입니다.
    웹 서버(예: Tomcat)에서 서블릿 컨테이너(Servlet Container)가 서블릿을 실행하고 관리합니다.

서블릿이 하는 일:
    1. 클라이언트 요청 처리:
    사용자가 웹 브라우저에서 요청을 보내면, 서블릿은 이 요청을 받아서 처리합니다.

    2. 로직 처리:
    서블릿은 요청을 처리하는 비즈니스 로직을 실행합니다.
    예를 들어, 데이터베이스에서 정보를 조회하거나, 입력된 데이터를 처리하는 등의 작업을 할 수 있습니다.

    3. 응답 생성:
    서블릿은 처리된 정보를 바탕으로 HTTP 응답을 생성해서 클라이언트에게 반환합니다.
    이 응답은 HTML, JSON, XML 등 다양한 형식일 수 있습니다.


 */

@WebServlet("/hello-servlet") // 꼭 주소 앞에 /를 써야한다.
// @annotation ; 컴파일시 검사하거나 자동완성하는 것
// @WebServlet ; 컴파일시 해당 클래스의 동적리소스 주소를 등록하는 것
public class HelloServlet extends HttpServlet { // HttpServlet를 꼭 상속받아야 한다.
    private String message;

    public void init() {
        message = "이거 뭔데, 웹앱서버 톰캣임";
    } // 무조건 실행되는 메서드

    // url (/hello-servlet)로 해당 리소스를 요청하면, doGet()이 실행된다.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HttpServletRequest는 응답을 생성하는 객체
        response.setContentType("text/html"); // html인데 text로 응답할거임.

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1 style = 'color:blue;'>" + message +(11*11)+ "</h1>");
        out.println("</body></html>");
    }

    public void destroy() { // 끝이 나면 실행
    }
}