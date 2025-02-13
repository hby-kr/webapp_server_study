package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// 동적리소스가 되려면, 두가지 작업이 선행되어야 한다.
//  1. ...extends HttpServlet  :  Servlet을 상속받아야 한다.
//  2. @WebServlet("/reqStudy.do")  : annotation으로 Servlet의 주소를 설정(매핑)해야 한다.
@WebServlet("/reqStudy.do")
public class L02Request extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //doGet(): HTTP GET 요청을 처리.

        // 클라이언트(웹브라우저)가 서버에 요청할 때 전달하는 정보 HttpServletRequest
        String url = req.getRequestURL().toString();   // url(Uniform Resource locator) 웹 페이지나 자원에 대한 전체 주소
        //      웹에서 자원을 찾을 수 있는 주소를 말합니다. 인터넷에서 특정 웹페이지나 파일을 찾기 위해 사용하는 주소
        String uri = req.getRequestURI();
        //      서버에서의 동적리소스 위치,  URI는 자원에 대한 고유한 식별자 역할
        //      그냥 path는 URL에서 자원에 접근하는 경로를 의미합니다. path는 서버가 처리해야 할 파일이나 서블릿, 리소스 등을 나타냅니다.
        String contextPath = req.getContextPath(); // 톰캣에서만 사용됨.
        //      톰캣은 한번에 여러 웹앱을 실행할 수 있는데, 복수의 웹앱들에게 상대경로(이름)을 지어 추가하는 것.
        //      톰캣(서버) 내의 웹 애플리케이션의 루트 경로를 의미합니다. 같은 이름일 경우에 임의의 경로를 각각 부여하는 것.
        String queryString = req.getQueryString();
        //      쿼리스트링이란, URL ? 뒤에 붙어 서버로 전달되는 데이터. 서버로 전달되는 추가적인 데이터나 파라미터를 포함.
        //      주로 폼 데이터나 필터링 정보 등을 전달할 때 사용.
        //      동적리소스는 함수다. (함수의 이름과 매개변수, 동작, return이 있듯) 매개변수(input값)인 쿼리스트링과, return인 response가 있다.

        System.out.println(url);  //  http://localhost:8181/webapp_server_study_war_exploded/reqStudy.do
        System.out.println(uri);  //           (서버주소)    /webapp_server_study_war_exploded/reqStudy.do
        System.out.println(contextPath); //                 /webapp_server_study_war_exploded             [이 웹앱의 상대 루트 경로]
        // URL: https://www.example.com:8080/myapp/search?query=java&sort=desc  / 자원의 위치를 나타내는 전체 주소
        // URI: https://www.example.com/myapp/search (이 부분이 자원 식별자)       /  쿼리 문자열을 제외한 경로 부분
        // Path:                       /myapp/search                            / 서버에서 자원이 위치하는 경로
        // ContextPath:                /myapp (애플리케이션 경로)                  / 서버 내에서 특정 웹 애플리케이션이 시작되는 지점입
        // QueryString:                 (?표 다음 것)      query=java&sort=desc   /  ? 뒤에 나오는 부분으로, 서버로 전달되는 추가 데이터
    }
}
