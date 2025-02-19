<%@ page import="com.tj703.webapp_server_study.model2.dto.M17deptDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 부서 전체 리스트</title>
</head>

<%
    Object deptListObj = request.getAttribute("deptList"); // 일단 뭐가 올지 모르니까 Object로 받음
    List<M17deptDto> deptList = null; // 그치만 난 List 파일로 받을거임
    if (deptListObj != null && deptListObj instanceof List) { // 정상적으로 와서, 비어있지 않고, list 타입이면..
        deptList = (List<M17deptDto>) request.getAttribute("deptList"); // 포워드로 받은 객체를 형번환해서 넣음
    } else {
        response.sendRedirect("../../index.jsp");
    }
%>

<body>
<h1> 부서 전체 리스트</h1>

<table>
    <thead>
    <tr>
        <th>부서코드</th>
        <th>부서명</th>
        <th>수정하기</th>
    </tr>
    </thead>

    <tbody>
    <%for (M17deptDto d : deptList) {%>
    <tr>
        <td><%=d.getDeptNoStr()%></td>
        <td><%=d.getDeptName()%></td>
        <td><a href="<%=request.getContextPath()%>/model2/deptModify.do?dept_no=<%=d.getDeptNoStr()%>"> 수정하기 </a></td>
    </tr>
    <%}%>
    </tbody>

    <hr>


</table>
<p><a href="<%=request.getContextPath()%>/model2/deptRegister.do">부서 등록하기</a></p>

</body>
</html>
