<%--
  Created by IntelliJ IDEA.
  User: tjoeun
  Date: 25. 2. 12.
  Time: 오후 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>부서 등록 페이지</title>
</head>
<body>
<h1>부서 등록 페이지</h1>

<hr>
<form method="post" action="<%=request.getContextPath()%>/model2/deptRegister.do">
    <p><label>
        부서번호 <input type="text" name="dept_no" placeholder="최대4자(ex. d1234)"/>
    </label></p>
    <p><label>
        부서명 <input type="text" name="dept_name" value="부서명을 입력하시오"/>
    </label></p>
    <p>
        <button type="reset">리셋</button>
        <button type="submit">제출</button>
    </p>
</form>
<hr>
</body>
</html>
