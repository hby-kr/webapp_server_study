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

/*  정적 리소스와 동적 리소스
    - 정적 리소스는 변경되지 않으며 클라이언트에 고정적으로 제공되는 콘텐츠
    - 동적 리소스는 서버에서 실시간으로 생성되는 콘텐츠이고,

    - 정적 리소스 (Static Resources)
        정적 리소스는 서버가 클라이언트에게 변경되지 않는 콘텐츠
        예를 들어, HTML 파일, CSS 파일, JavaScript 파일, 이미지(JPG, PNG 등) 등

    - 동적 리소스 (Dynamic Resources)
        동적 리소스는 서버에서 실행된 코드나 데이터를 기반으로 실시간으로 생성되는 콘텐츠
        클라이언트의 요청에 따라 데이터를 처리하거나, 데이터베이스와 연동하여 최종적으로 HTML, JSON, XML 등의 형식으로 결과를 반환
        그때에 서버에서 사용하는 도구가 서블릿, JSP, 템플릿 엔진(Thymeleaf)
 */


// DB에 접속해서 부서목록을 화면에 띄워보자.
// MODEL1 (무식한 방법) (웹 애플리케이션의 프레젠테이션 로직과 비즈니스 로직을 모두 처리하게 만드는 방식)
@WebServlet("/jdbcDeptList.do")
public class L05JdbcDeptList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. DB 커넥션 후 자료 가져오기
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
            // throw new RuntimeException(e); // throw하면 catch가 바로 종료된다.
            // throw 하기보다 오류를 나타내는 방식으로 처리할 수 있음.
            resp.sendError(500);
            return; // 이 방식을 추천.
            // 서버의 중요한 내역을 에러로 배포하면 해킹의 위험이 있다.
        }


        // 2. 화면에 가져온 DB 자료 뿌리기
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
model 1 : 동적 페이지에서 접속하고 출력하는 모든 코드가 모여있는 디자인
          모든 비즈니스 로직과 뷰(출력) 코드가 한 곳에 모여 있는 디자인 패턴

model 2 :  MVC (Model-View-Controller) 패턴  / Model = dao , View = html, Controller = Servlet
      Model (모델): =dao          / 데이터를 가져오거나 데이터를 저장하는 역할
      View (뷰): =html, jsp       / 사용자에게 출력을 제공하는 컴포넌트. 모델로부터 데이터를 받아서 사용자에게 보여줌.
      Controller (모델): =Servlet / 사용자의 요청을 처리하고, 필요한 모델과 뷰를 연결하는 역할

MVC 패턴에서의 역할 분리:
    Model (모델): Java 파일, 특히 서블릿이나 서비스 클래스가 데이터 처리 및 비즈니스 로직을 담당
    View (뷰): JSP 파일은 UI를 담당하여 데이터를 출력하고, 사용자에게 보여주는 웹 페이지를 렌더링합니다.
    Controller (컨트롤러): Java 서블릿이나 Spring Controller와 같은 Java 파일이 요청을 처리하고, 적절한 JSP 파일을 호출하여 데이터를 전달합니다.


>> MVC 세부적으로 다시

1. Model
    Model에 해당하는 주요 코드 파일은 크게 둘,
    서비스 클래스(Business Service)와 DAO(Data Access Object)입니다.
    - 서비스 클래스는 비즈니스 로직을 처리하고, 실제 업무 흐름을 관리하는 역할
    - DAO는 데이터베이스와의 연결을 담당하며, 데이터를 조회, 삽입, 수정, 삭제하는 작업을 처리

    1) 서비스 클래스
        Java 파일 안에 있는 클래스는 보통 객체 지향 프로그래밍(OOP) 관점에서 비즈니스 로직을 처리합니다.
        예를 들어, 데이터 처리, 서비스 로직, 도메인 모델 등을 담당하는 클래스들이죠.
        이러한 클래스는 Servlet과는 직접적인 관계 없이 독립적으로 존재할 수 있습니다.
    2) DAO ; DB접속



2. View
    -  JSP(JavaServer Pages) 또는 HTML (동적/정적 웹 페이지)  + CSS(스타일시트),JavaScript (클라이언트 측 로직)
    -  템플릿 엔진 파일 (예: Thymeleaf, FreeMarker)

? JSP와 html
    1) JSP (JavaServer Pages):
        동적 웹 페이지를 생성하는 데 사용되는 템플릿 파일입니다.
        HTML과 Java 코드가 결합된 형태로,
        서블릿에서 전달된 데이터를 출력하는 데 사용
        JSP는 서버 측에서 처리되고, 클라이언트에게 HTML을 전송하는 방식으로 동작
    1-2) JSP외에 다른 뷰 템플릿 엔진 (Thymeleaf, FreeMarker 등):
        Thymeleaf는 Spring과 같은 프레임워크에서 자주 사용되는 템플릿 엔진
        JSP는 Java 코드가 직접 HTML 내에 포함되어 있어 비즈니스 로직과 뷰가 결합되는 경향이 있지만,
        템플릿 엔진(예: Thymeleaf)은 HTML 템플릿을 비즈니스 로직과 분리할 수 있어, 뷰의 책임을 더 명확히 분리할 수 있습니다.

    2) HTML
        정적 웹 페이지로, JSP와 달리 서버에서 동적으로 처리되지 않고 클라이언트에게 그대로 전달되는 파일
        HTML 파일은 일반적으로 프론트엔드 작업에 사용되며,
        자바스크립트, CSS와 결합되어 페이지의 외형을 꾸미고 사용자 인터페이스를 구성
    2-2) JavaScript (클라이언트 측 로직)
        클라이언트 측에서 동적 기능을 구현하는 데 사용
        사용자와의 상호작용을 처리하거나,
        페이지를 새로고침하지 않고 데이터를 동적으로 처리하는 데 사용



3. Controller
    - Client의 요청을 받아 Client가 보낸 Data를 읽고 검사한다.
    - Model에게 Business Logic을 요청한다. (객체지향이라 기능별로 나눠져있는 java파일 호출하면 됨)(컨트롤러도, model도 자바파일이니까.)
    - Model의 처리 결과는 맞는 View에게 응답을 요청한다. (포워딩)
        포워딩; Controller가 요청을 처리한 후, 해당 요청을 뷰 템플릿(JSP, Thymeleaf 등)으로 전달하는 과정

    사용자가 요청을 보내면, 컨트롤러는 모델을 호출하여 필요한 데이터를 처리하고, 그 결과를 동적으로 생성된 뷰에 전달(포워딩)합니다.
    정적 리소스를 제공하는 경우, 컨트롤러는 정적 리소스에 대한 경로를 반환하여 클라이언트가 이를 요청할 수 있도록 합니다.

    Servlet은 Java에서 컨트롤러(MVC 중 Controller) 역할을 하는 도구 중 하나.
        (그 밖에 PHP 프레임워크인 Laravel이나 Symfony에서는 컨트롤러 클래스를 정의
        Python에서는 Django나 Flask가, JavaScript의 Node.js와 Express 프레임워크에서는 라우터(router)가 있음)
    서블릿은 웹 애플리케이션의 서버(WAS) 측에서 HTTP 요청(request)을 처리, 반환하는 Java 클래스 중 하나
    서블릿은 웹 요청(HTTP Request)을 받아서 처리한 후 웹 응답(HTTP Response)을 보냅니다.
        1) 요청 처리: 클라이언트(사용자)가 보내는 HTTP 요청을 받아 처리합니다.
        2) 모델 호출: doGet()이나 doPost() 메서드를 통해 받은 HTTP 요청을
            비즈니스 로직을 처리하는 서비스 클래스나 DAO를 호출하여 데이터를 처리합니다.
        3) 응답 생성: 처리된 데이터를 바탕으로 동적 HTML을 생성하거나, 다른 포맷(JSON, XML 등)을 만들어 뷰로 데이터를 전달반환합니다.

 */