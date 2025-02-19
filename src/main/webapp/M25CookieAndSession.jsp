<%@ page import="com.tj703.webapp_server_study.model2_service.dto.UserDto" %><%--
  Created by IntelliJ IDEA.
  User: tjoeun
  Date: 25. 2. 19.
  Time: 오후 6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인하고 세션으로 유지 + 배너창으로 쿠기 만들기</title>
</head>
<body>
<h1>로그인하고 세션으로 유지 + 배너창</h1>

<h2> 팝업창에 쿠키 만들어서 쿠키가 유효하는 동안 팝업 안뜨기</h2>
<%
    Cookie[] cookies = request.getCookies(); // 모든 쿠키 다 받음
    Cookie isBannerCookie = null;            // isBannerCookie 쿠키 받을 준비
    if (cookies != null) {                   // 쿠키가 있으면
        for (Cookie c : cookies) {           // 전체 돌려서
            if (c.getName().equals("isBannerCookie")) { // isBannerCookie를 찾고
                isBannerCookie = c;          // 준비된 쿠키 객체에 넣기
            }
        }
    }

    if (isBannerCookie == null || !isBannerCookie.getValue().equals("1")) {
        // 즉 isBannerCookie가 정상적이지 않다면, 팝업을 다시 띄워야 할 것 같다면
        // 아래 팝업을 실행.
%>
<script>
    window.open("./M25Banner_homework.jsp", "_blank", "width=800,height=400,left=100,top=100");
</script>
<%}%>
<hr>



<h2>users DB로 로그인 만들고 session에 DepartmentDto deptDto 저장</h2>
<h3>(로그인 성공시 form 을 보이지 않게 하고, "사용자정보 로그인 중" 구현)</h3>

<%-- 작업 2--%>
<%
    Object userDtoObj = session.getAttribute("userDto");
    Object msg = session.getAttribute("msg");

%>
<%
    if(msg!=null){
    session.removeAttribute("msg");
%> <script>alert("<%=msg%>");</script>
<%}%>


<%if(userDtoObj==null){%>  <%--// 로그인 실패 했다면.. --%>
    <%-- 작업 1--%>
    <form action="./M25Login.do" method="post">
        <p><label><input type="text" name="email" value="user1@example.com" ></label></p>
        <p><label><input type="text" name="password" value="1234" ></label></p>
        <p><button>로그인</button></p>
    </form>
<%}else{ // 로그인 성공까지 했으면
    UserDto userDto= (UserDto) userDtoObj;
%>
<p>
    <%=userDto.getEmail()%> 님 로그인 중
    <br> <hr>
    <a href="./M25logout.do">로그아웃</a>
</p>
<%}%>








</body>
</html>
