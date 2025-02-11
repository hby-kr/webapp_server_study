<%--  doget(request,response) 여기가 doGet 내부라고 생각하면 됨 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--   == resp.setContentType("text/html;charset=UTF-8");    --%>

<html>
<head>
<%--  안보이지만
PrintWriter out = resp.getWriter();
out.println("<html>");
out.println("<head>");
이렇게 쓴 것과 마찬가지로 되어 있는 것.

JSP는 서버에서 서블릿으로 변환되어 실행되며, 이때 doGet() 메서드 안에서 실행되는 코드처럼 처리됩니다.
JSP 코드를 작성하면 내부적으로 서블릿의 doGet() 메서드가 이를 처리하고 클라이언트에게 응답을 반환하는 구조임.
1. 클라이언트가 JSP 파일을 요청합니다.
2. 서버는 이 JSP 파일을 서블릿으로 변환하고, 그 서블릿의 doGet() 메서드에서 JSP 코드를 처리합니다.
3. 결국, JSP 코드의 실행 결과는 서블릿의 doGet() 메서드 내에서 처리된 결과로 응답됩니다.

--%>
    <title>Title</title>
</head>
<body>

<h1> View template jsp (java servlet page) </h1>
<%--
뷰 템플릿은 HTML, XML, JSON, 또는 다른 형태의 출력 형식
뷰 템플릿 엔진은 서버에서 동적으로 HTML 등의 뷰템플릿을 생성
따라서 뷰 템플릿 엔진은 뷰를 구현하는 데 도움을 주는 도구입니다.
주로 HTML 파일에 데이터를 동적으로 삽입하거나 변형하여 사용자에게 보여줄 콘텐츠를 생성하는 데 사용.

예시 템플릿 엔진:
    Jinja2: Python에서 Flask와 같은 프레임워크에서 사용됩니다.
    Thymeleaf: Java 기반 웹 애플리케이션에서 자주 사용됩니다.
    Handlebars.js: JavaScript에서 사용됩니다.

--%>

<p> jsp는 서블릿에서 출력 부분이 자바 코드가 작성 부분과 역전된 페이지. 뷰view가 메인, 자바코드가 sub</p>
<p> 결국 이곳이 doGet 내부 코딩공간과 같다.</p>
<p> jsp는 문서는 javac가 아닌 다른 컴파일러에 의해서 실시간으로 컴파일 실행된다.  => 장점: 배포를 계속 할 필요가 없다. = 시간절약 </p>
<p>  = 실시간으로 컴파일 실행되는 언어를 인터프리터 언어, 스크립트 언어가 있다. 이런 언어를 뷰 템플릿 언어라고 말한다. </p>


<p> 수학계산 가능
    13*88 =
    <%
    out.println(13*88); // 자바코드 내부 / doGet()함수 내부임
     %>
</p>

<p> 백엔드에서 view를 렌더링하는 문서를 뷰템플릿(view template engine)이라고 한다.
    보통 서버언어(java)를 컴파일하거나 실행하는 엔진(javac, jvm)이 있고,
    뷰템플릿을 컴파일하고 실행하는 엔진이 따로 존재해서, 그것을 뷰템플릿 엔진이라고 부른다. </p>
<p>Thymeleaf는 Java 애플리케이션에서 HTML, XML, JavaScript, CSS 파일을 렌더링하기 위한 서버 측 템플릿 엔진</p>


<h2> JSP를 사용하는 이유</h2>
<ul>
    <li>servlet에서 view를 작성하기 힘들기 때문</li>
    <li>servlet이 자바로 되어 있어서, 수정하면 매번 컴파일 배포해야 하기 때문에 개발 속도가 느려진다.</li>
    <li>service(DB)(=Model)와 servlet(요청-응답처리)(Controller)과 view(UI html)를 구분하기 위해서</li>
    <%-- 컨트롤러와 분리: MVC 패턴에서 JSP는 View 역할을 맡고, 데이터를 표시하는 데 사용됩니다.
    실제 비즈니스 로직이나 데이터 처리는 Controller나 Model에서 처리되며, JSP는 그 결과를 사용자에게 보여주는 역할을 합니다.--%>
    <li>프론트 개발자(html,css,js)들에게 화면구현을 시키기 위해서, 프론트 개발자(퍼블리셔)가 개발을 편히하기 위해서.</li>
</ul>


<h2>구현 뷰 템플릿 엔진 JSP</h2>
<ul>
    <li>뷰 템플릿 엔진을 배포가 되는 위치에 둘 수 있기 때문에(정적 리소르로 취급 되기 때문에) 리소스를 갈취할 수 있다</li>
    <li>정적리소스는 그냥 바로 클라이언트로 보내기 때문. 동적로딩을 실행해서 결과만 보내지만.</li>
    <li>jsp는 java에서 할 수 있는 모든 코드를 작성할 수 있어서 보안에 취약함</li>
    <li>태그 외의 코드를 작성(< %   % >)하기 때문에 전반적으로 지저분하고 재사용이 어렵다.</li>
    <%--  JSP는 전통적인 뷰 템플릿 엔진처럼 동적으로 HTML을 생성하지만,
        템플릿 엔진은 보통 로직과 표시를 분리하여 더 깔끔하게 관리하려는 목적을 가지고 있습니다.
        JSP에서는 Java 코드와 UI 코드가 섞이기 때문에,
        일부에서는 "로직과 표시가 분리되지 않았다"는 이유로 템플릿 엔진으로서의 한계가 있다고 볼 수 있습니다.   --%>
</ul>

</body>
</html>

<%! // 서블릿 클래스의 전역  / 느낌표 써서 변수 설정
    int a = 10;
    public static final String NAME = "최경민";
%>
<% 
    int a = 10;
    int b = a*this.a;
%>
    
<%--  doPost(request, response) {
        doGet(request, response)
        // post로 호출해도 get으로 호출된다.
}--%>