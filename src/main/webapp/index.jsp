<%@ page import="com.tj703.webapp_server_study.model2_service.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>JSP - Hello World</title>
</head>
<body>
<%--
<% %>와 <%= %>는 JSP (JavaServer Pages)에서 사용하는 스크립트 태그
1. <% %> — 스크립트let (Scriptlet)
    <% %>는 Java 코드를 삽입하는 데 사용.
    이 태그 내부에 Java 코드를 작성할 수 있습니다.
    이 코드 내에서 변수 선언, 조건문, 반복문 등을 사용할 수 있음
    그러나, 출력 결과는 HTML에 포함되지 않습니다.
    즉, <% %> 내부의 코드는 서버에서 실행되지만 클라이언트로 직접 출력되지 않습니다.

2. <%= %> — 표현식 (Expression)
    <%= %>는 Java 코드를 실행하고 그 결과를 HTML로 출력하는 데 사용.
    이 태그 내부에서 계산된 값이나 변수는 결과가 자동으로 출력됩니다.
    즉, <%= %>는 Java 코드를 실행한 결과를 HTML 페이지에 포함시키는 역할
    즉, out.print()나 out.println()을 명시적으로 사용하지 않고 결과값을 바로 출력할 수 있도록 해줍니다.
--%>

<%
    // 출력한 index 페이지도 또한 서버의 응답이지 않겠나.
    // 따라서 설정해놨다면 이미 쿠키가 있을 수 있음.
    Cookie[] cookies = request.getCookies();  // 쿠키 가져와서.
    Cookie isBannerCookie = null;  // 받을 객체 하나 만들고

    if (cookies != null) {  // 쿠키가 있는 상태라면
        for (Cookie c : cookies) {  // 쿠키 중 탐색
            if (c.getName().equals("isBannerCookie")) { // 이름이 isBannerCookie인 녀석을 찾아서
                isBannerCookie = c; // 그 녀석을 준비해놓은 객체에다가 대입
            }
        }
    }
%>

<%
    if (isBannerCookie == null || !isBannerCookie.getValue().equals("1")) {
        // 준비해놓은 쿠키(하루동안 안보기는 사람한테 준 쿠키)가 없으면.. == 처음 접속이거나 쿠키가 만료되었으면. 아래의 팝업 실행 %>
<script type="text/javascript">
    window.open("./L25Banner.jsp", "_blank", "width=800,height=200,left=100,top=100");
    <%--window.open("http://localhost:8181/<%=request.getContextPath()%>/L25Banner.jsp", "_blank", "width=800,height=200,left=100,top=100");--%>
    // 절대 경로로 URL을 넣으면 브라우져에서 새로운 접속으로 해서, 새로운 JSESSIONID를 서버에서 얻게 된다.
    // 상대 경로를 URL을 넣으면 기존 접속을 바탕으로 하므로, 기존 JSESSIONID을 가진 채로 진행되게 된다.
</script>
<%}%>

<%--
   /*  window.open(URL, name, specs, replace);
    window.open()은 JavaScript에서 새 브라우저 창이나 탭을 여는 데 사용되는 메서드
    (URL, name, specs, replace);
    - URL (optional): 새로 열려는 페이지의 URL입니다. 이 매개변수가 생략되면 빈 페이지가 열립니다. 기본값은 빈 문자열입니다.
    - name (optional): 새로 열리는 창의 이름을 지정하는데 사용. 이 값은 창을 식별할 수 있는 고유한 이름이어야 하며, 새 창이 아닌 기존 창을 열 때 사용됩니다.
        예를 들어, "blank"는 새 탭을 의미하고, "self"는 현재 창을 의미합니다.
            _self: 현재 창에서 페이지를 로드 (기존 페이지를 대체)
            _blank: 새 탭이나 새 창에서 페이지를 로드
            _top: 최상위 창에서 페이지를 로드 (프레임이 있을 경우)
            _parent: 부모 프레임에서 페이지를 로드 (프레임 내에서 사용)
            사용자 지정 이름: 이름을 지정하여 특정 창을 타겟으로 페이지를 로드; 이름을 지정하면 동일한 이름을 가진 새 창이나 탭에서 열린 페이지는 기존 창을 덮어쓰게 됩니다. 즉, 새로 열린 창이 기존 창을 대체하게 됩니다.
    - specs (optional): 새 창의 크기, 위치, 기타 속성에 대한 설정입니다.
    문자열로, 쉼표로 구분된 여러 가지 옵션을 지정할 수 있습니다. 예를 들어, width=500,height=400와 같이 사용할 수 있습니다.
    - replace (optional):
    Boolean 값으로, true이면 새 URL이 히스토리 스택을 대체하고, false이면 새 URL이 히스토리 스택에 추가됩니다. 대부분의 경우 이 값은 생략하거나 false로 설정됩니다.
    */
--%>


<%-- jsp 파일을 템플릿(Template)으로 가져오기
이 지시어는 페이지 내에서 다른 파일의 내용을 서버 측에서 삽입(included) 할 수 있도록 도와줌
템플릿(Template)은 일반적으로 웹 개발에서 구조를 정의하고 재사용 가능한 형식을 제공하는 방식 --%>
<%--    서버 내부에서 루트 상대 경로는 /webapp 하위다.   --%>
<%@include file="/WEB-INF/views/service/header.jsp"%>  <br><br><br><br>

<h1> 웹 앱 서버 </h1>

<h2>수업 링크</h2>
<ul>
    <li><a href="hello-servlet">hello-servlet</a></li>
    <li><a href="resStudy.do">응답 수업</a></li>
    <li><a href="reqStudy.do">요청 수업</a></li>
    <li><a href="queryStringStrudy.do?name=abce&age=30">쿼리스트링 수업 (파라미터 name, age)</a></li>
    <li><a href="errorStudy.do">에러 처리 수업</a></li>
    <li><a href="jdbcDeptList.do">JDBC 부서 리스트 수업</a></li>
    <br>

    <li><a href="empCRUDList.do">JDBC "사원" 관리CRUD 웹앱 수업 (model1)</a></li>
    <li><a href="deptCRUD.do">JDBC "부서" 관리CRUD 웹앱 과제 (model1) (연습과제) </a></li>
    <br>

    <li><a href="L12ViewTemplateJSP.jsp">View Template JSP (개념)</a></li>
    <li><a href="L13DeptList.jsp"> JSP로 구현하는 "부서"리스트 (jsp로 db까지 접속하기)</a></li>
    <br>

    <li><a href="model2/empList.do">모델2로 구현한 "사원" 리스트</a></li>
    <li><a href="model2/deptList.do">모델2로 구현한 "부서" 리스트 (연습과제)</a></li>
    <br>

    <li><a href="setCookies.do">쿠키 만드는 setcookie작업</a></li>
    <li><a href="getCookies.do">쿠키 가져오는 getcookie작업</a></li>
    <li><a href="setSession.do">세션 만들기</a></li>
    <p>
        <%
            // 위 세션만들기 링크를 갔다오면서 세션이 (최초든 기존이든) 만들어져 있는 상태
            Object emailObj = session.getAttribute("email");
            Object nameObj = session.getAttribute("name");
            Object marriedObj = session.getAttribute("married");
            Object pwObj = session.getAttribute("pw");

        %>
        <b><%=emailObj%>(<%=nameObj%>, <%=marriedObj%>, <%=pwObj%>) 님 로그인 중</b>

    </p>

    <li><a href="M25CookieAndSession.jsp">부서정보(이름과 부서번호)로 로그인 + session 기능 (연습 과제)</a></li>
    <br>

    <li><a href="service/login.do">UserManagement DB 유저 로그인 예제</a></li>


</ul>
<br>
<hr>

<div>
    <!-- action이 요청하는 곳, 주소를 쓰면 됨   -->
    <form method="post" action="postStudy.do">
        <p><label>
            유저계정 :<input type="text" name="userId" value="경만">
        </label></p>
        <p><label>
            비밀번호 :<input type="password" name="userPw" value="123456">
        </label></p>
        <p>
            <button type="button">그냥버튼</button>
            <button type="reset">양식 내부의 input 값을 최초값으로 변경</button>
            <button type="submit">제출</button>
        </p>
    </form>
</div>
<br>
<hr>


<h2>경로 수업</h2>

<!--
WAS(웹앱서버) 개발에서 경로를 분류하면 일단 크게 둘이다.  (절대, 상대 다 접어두고.)
1) 서버 내부 경로(실제 경로)
2) 서블릿 매핑 URL 경로 (가상으로 만든 경로)
어느 것을 어떻게 사용할지에 대한 차이는
요청이 서버 내에서 처리되는지, 아니면 클라이언트에서 요청되는지이다.

1) (내부에서만)
    서버 내부 경로는 서버 내에서 요청을 포워드할 때 사용.
    즉 클라이언트가 요청하는 URL과는 별개로, 서버가 요청을 내부적으로 다른 리소스로 전달할 때 사용
    - 서버 내에서 JSP 파일을 동적으로 렌더링하려는 경우
    - 다른 서블릿으로 포워드할 때(서버에서 요청을 다른 서블릿으로 전달하여 다른 로직을 실행하게 할 때)
    클라이언트는 이 경로를 직접 알지 못합니다.

2) (외부에서 내부로)
    서블릿 매핑 URL은 클라이언트가 요청을 보낼 때 사용하는 URL 경로.
    이 URL을 통해 클라이언트는 서버에 HTTP 요청을 보내고, 서버는 그 요청에 맞는 서블릿이나 컨트롤러가 처리하도록 매핑.


ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ  서버 내부 경로든,  서블릿 매핑 URL이든 경로를 적는 방식이 있는데
그게 크게 절대경로와 상대경로로 나눠 설명할 수 있다.


가상 주소가 이렇다고 치자.
http://localhost:8080/country/province/region/home/myapp/index.jsp
http://localhost:8080/                                              [호스트와 포트 번호, 루트경로]
                                                                        cf. 서버 루트경로와 어플리케이션 루트경로는 다르다.
                      country/province/region/home/myapp/index.jsp  [절대경로로 파일 접근하기]
                      country/province/region/home/myapp/           [애플리케이션 루트 경로] (myapp 폴더(디렉토리)에서 서비스가 구동되는 것임)
                                                                     (즉, 웹 애플리케이션이 서버에 배포된 위치)
                                                   myapp/           [context path] (여러 웹앱을 서버에서 동시에 실행하기 위해 생성한 경로)
                                                        /index.jsp  [최종적로 요청된 리소스, 내가 열고 있는 파일]

루트경로;   Root 경로는 웹 애플리케이션이 서버에 배포된 위치. 웹 서버에서 애플리케이션의 최상위 디렉토리.
절대경로;   현재 위치와 무관하게 웹 서버의 "루트 경로"에서 시작하는 경로
상대경로;   상대 경로는 현재 문서나 리소스의 위치를 기준으로 다른 리소스를 찾는 경로
현재위치;   상대주소의 "현재 위치"는 현재 문서나 리소스의 URL을 기준으로 정의

>> 절대경로
/로 시작하면 절대경로임. 루트부터 찾음.

/없이 myapp.jsp라고 그냥 경로, 이름을 쓰거나,
쩜으로 시작하거나 ./
쩜쩜으로 시작하면 ../  상대경로. 현재기준임.

절대경로  /about.jsp는
        "루트 경로에서 about.jsp를 찾으라"이므로
        http://localhost:8080/about.jsp로 요청

절대경로  /myapp/index.jsp는
        "루트 경로에서 myapp/index.jsp를 찾으라"이므로
        http://localhost:8080/myapp/index.jsp로 요청

상대경로   about.jsp와
        ./about.jsp는 값음.
        "지금 위치에서 about.jsp를 찾으라"
        http://localhost:8080/country/province/region/home/myapp/about.jsp를 가리킵니다.
        
상대경로 ../index.jsp는
        "상위 디렉토리에서 index.jsp를 찾으라"으므로
         http://localhost:8080/country/province/region/home/index.jsp로 해석됩니다.

상대경로 ../../otherpage.jsp는
        "두 단계 상위 디렉토리에서 otherpage.jsp를 찾으라"이므로
         http://localhost:8080/country/province/otherpage.jsp로 해석됩니다.

-->
<ul>
    <li>절대경로 :( https://naver.com, c://windows/.... ) 현재 위치와 무관하게 해당 경로의 리소스를 찾는다.</li>
    <br>
    <li>절대경로 : (/book.com) (/로 시작!!) (현재 위치 상관없이) 현재위치에서 기준으로 최상위 경로(root경로)로 이동해서 book.com 을 찾는다
        <ol>
            <li>현재위치 https://naver.com/store/detail/web/detail.do => https://naver.com/book.com</li>
            <li>현재위치 c://windows/store/detail/web/ => c://book.com</li>
            <li>현재위치 /Users/desktop/tomcat/ => /book.com</li>
        </ol>
    </li>
    <hr>
    <br>

    <li>상대경로 : (./book.com 이거나 book.com) 현재 위치에서 리소스 탐색 ex) ./home/book.com과 home/book.com도 동일한 위치
        <ol>
            <li>현재위치
                https://naver.com/store/detail/web/detail.do
                =>
                https://naver.com/store/detail/web/book.com
            </li>
            <li>현재위치 c://windows/store/detail/web/ => c://windows/store/detail/web/book.com</li>
            <li>현재위치 /Users/desktop/tomcat/ => /Users/desktop/tomcat/book.com</li>
        </ol>
    </li>
    <li>상대경로 : (../book.com or ..book.com ) 현재 경로의 상위 폴더에 있는 리소스 탐색
        <ol>
            <li>현재위치
                https://naver.com/store/detail/web/detail.do
                =>
                https://naver.com/store/detail/book.com
            </li>
            <li>현재위치 c://windows/store/detail/web/ => c://windows/store/detail/book.com</li>
            <li>현재위치 /Users/desktop/tomcat/ => /Users/desktop/book.com</li>
        </ol>
    </li>
    <li>상대경로 : (../../book.com) 현재 경로의 이전 폴더에 있는 리소스 탐색
        <ol>
            <li>현재위치
                https://naver.com/store/detail/web/detail.do
                =>
                https://naver.com/store/book.com
            </li>
            <li>현재위치 c://windows/store/detail/web/ => c://windows/store/book.com</li>
            <li>현재위치 /Users/desktop/tomcat/ => /Users/book.com</li>
        </ol>
    </li>
</ul>


</body>
</html>