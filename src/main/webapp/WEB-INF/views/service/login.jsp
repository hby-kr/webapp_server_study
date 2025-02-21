<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserManagement DB 로그인 화면</title>
</head>
<body>

<%@include file="/WEB-INF/views/service/header.jsp" %>
<br><br><br><br>

<h1>UserManagement DB 로그인 화면</h1>
<div>
    <form action="login.do" method="post">
        <p><label>
            <b>user 이메일</b><input type="text" name="email" value="user1@example.com"/>
        </label></p>
        <p><label>
            <b>비밀번호</b><input type="text" name="password" value="1234"/>
        </label></p>
        <p><label>
            <b>아이디 저장 (쿠키 활용)</b><input type="checkbox" name="auto_email" value="1"/>
        </label></p>
        <p><label>
            <b>한달간 로그인 유지하기 (쿠키 활용)</b><input type="checkbox" name="auto_login" value="1"/>
        </label></p>
        <p>
            <a href="./signup.do">회원가입</a>
            <%-- a태그가 더 권장. 왜냐면 a태그는 검색엔진과 navigation 웹에서 시멘틱 요소 역할을 하므로.--%>
            <button type="submit">로그인</button>
        </p>

    </form>
</div>

</body>
</html>
