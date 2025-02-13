<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>부서 상세 및 수정</title>
</head>

<%
    // /L14DeptDetail.do에서 포워딩 할 때, req.setAttribute("dept", dept); 라고 포워드(렌더링)할 jsp에 dept라는 객체를 전달하기
    Object deptObj = request.getAttribute("dept"); // 그 객체 받기
    Map<String, String> dept = (Map<String, String>) deptObj; // 원상태로 돌리는 캐스팅
%>

<body>
<h1>부서상세 및 수정</h1>
<form action="L15deptModifyAction.do" method="post">
    <p>
        <label>
            부서 번호 : <input type="text" name="dept_no" readonly value="<%=dept.get("dept_no")%>">
        </label>
    </p>
    <p>
        <label>
            부서 번호 : <input type="text" name="dept_name" value="<%=dept.get("dept_name")%>">
        </label>
    </p>
    <button type="reset">초기화</button>
    <button type="submit">제출</button>

</form>


</body>
</html>
