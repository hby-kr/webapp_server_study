<%@ page import="com.tj703.webapp_server_study.model2.dto.L17EmpDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title> 사원 상세 및 수정 폼</title>
</head>
<body>
<h1> 사원 상세 및 수정 폼</h1>
<%
    // 포워드 받은 객체인 파라미터 받기
    L17EmpDto emp = (L17EmpDto) request.getAttribute("emp");
%>

<form method="POST" action="<%=request.getContextPath()%>/model2/empModify.do">
    <%--  request.getContextPath()를 썼던 것은, 서비스의 경로가 복잡해지면서
     현재 서비스(어플리케이션)의 root경로로 빠르게 가서, 거기서부터 다시 경로를 쓰기 위해서였음.
     내가 servlet을 통해,  contextpath()/model2/empList.do 가 있다고 맵핑해놓은 상태임 --%>
    <p><label>
        사번 <input type="text" name="emp_no" value="<%=emp.getEmpno()%>" readonly/>
    </label></p>
    <p><label>
        이름 <input type="text" name="first_name" value="<%=emp.getFirstname()%>"/>
    </label></p>
    <p><label>
        성 <input type="text" name="last_name" value="<%=emp.getLastname()%>"/>
    </label></p>
    <p><label>
        남성 <input type="radio" name="gender" value="M"
            <%if (emp.getGender().equals("M")) {%> checked
            <%}%> />
        여성 <input type="radio" name="gender" value="F"
            <%if (emp.getGender().equals("F")) {%> checked
            <%}%> />
    </label></p>
    <p><label>
        생일 <input type="date" name="birth_date" value="<%=emp.getBirthday()%>"/>
    </label></p>
    <p><label>
        입사일 <input type="date" name="hire_date" value="<%=emp.getHiredate()%>"/>
    </label></p>
    <p>
        <button type="reset">초기화</button>
        <button type="submit">제출</button>
    </p>
    <p><a href="<%=request.getContextPath()%>/model2/empDelete.do?emp_no=<%=emp.getEmpno()%>">회원정보 삭제</a></p>
</form>
</body>
</html>
