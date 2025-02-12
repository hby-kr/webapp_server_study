<%@ page import="com.tj703.webapp_server_study.model2.dto.L17EmpDto" %>
<%@ page import="com.tj703.webapp_server_study.model2.dto.M17deptDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>부서 수정 가능 페이지</title>
</head>

<body>
<h1>부서 수정 가능 페이지</h1>

<%
    // 객체 받아서 혹시나 다시 형변환
    M17deptDto dept = (M17deptDto) request.getAttribute("dept");
%>

<hr>
<form method="post" action="<%=request.getContextPath()%>/model2/deptModify.do">
    <p><label>
        부서번호 <input type="text" name="dept_no" value="<%=dept.getDeptNoStr()%>" readonly />
    </label></p>
    <p><label>
    부서명 <input type="text" name="dept_name" value="<%=dept.getDeptName()%>" />
    </label></p>
    <p>
        <button type="reset">리셋</button>
        <button type="submit">제출</button>
    </p>
</form>
<hr>
<p><a href="<%=request.getContextPath()%>/model2/deptDelete.do?dept_no=<%=dept.getDeptNoStr()%>">삭제하기</a></p>

</body>
</html>
