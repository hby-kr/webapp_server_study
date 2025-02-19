<%@ page import="com.tj703.webapp_server_study.model2.dto.L17EmpDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>사원 리스트</title>
</head>
<%--
JSP는 서버에서 서블릿으로 변환되어 실행되며, 이때 doGet() 메서드 안에서 실행되는 코드처럼 처리됩니다.
JSP 코드를 작성하면 내부적으로 서블릿의 doGet() 메서드가 이를 처리하고 클라이언트에게 응답을 반환하는 구조임.
1. 클라이언트가 JSP 파일을 요청합니다.
2. 서버는 이 JSP 파일을 서블릿으로 변환하고, 그 서블릿의 doGet() 메서드에서 JSP 코드를 처리합니다.
3. 결국, JSP 코드의 실행 결과는 서블릿의 doGet() 메서드 내에서 처리된 결과로 응답됩니다.
--%>


<body>
<h1>사원 리스트</h1>



<%-- 3. (웹구조로 인해 위로 왔을 뿐) crud 중 creat를 위한 자리 만들기 --%>
<p><a href="<%=request.getContextPath()%>/model2/empRegister.do"> 사원 등록하기 </a></p>
        <%-- 포워드 받은 JSP 페이지에서 request.getContextPath()를 호출하면,
        그 request 객체는 실제로 포워드한 Java 서블릿에서 생성된 것이므로,
        getContextPath()의 반환 값은 서블릿이 실행되고 있는 애플리케이션의 컨텍스트 경로
        여기서는  http://localhost:8181/web_app_server_study_war_exploded 이 됨
        --%>



<%-- 1. 포워드 받았으니 받은 매개변수부터 받아서 뿌릴 준비하기   --%>
<%  Object empListObj = request.getAttribute("empList");
    List<L17EmpDto> empList = null;

    if (empListObj != null && empListObj instanceof List) { // 비어있지 않고, List 타입으로 잘 왔으면. 즉 성공했으면.
        empList = (List<L17EmpDto>) request.getAttribute("empList"); // 형변환해서 제대로 잘 넣어.
        // out.println(empList); // 테스트 해봤음.
        // jsp파일이 PrintWriter out = resp.getWriter();가 이미 정의 되어있는 상태임. 그래서 바로 out.print()를 해버릴 수 있는 것
    } else {
        response.sendRedirect("./index.jsp");
    };
%>

<%-- 2. 직접 VIEW로 뿌리는 작업--%>
<table>
    <thead>
    <tr>
        <th>사번</th>
        <th>이름(이름+성)</th>
        <th>생일</th>
        <th>상세보기(수정가능)</th>
    </tr>
    </thead>

    <tbody>

    <%for (L17EmpDto e : empList) {%>  <%-- 리스트에 있는 사람정보 하나하나 반복할 거임.   --%>
    <tr>
        <td><%=e.getEmpno()%></td>
        <td><%=e.getFirstname()%> &nbsp; <%=e.getLastname()%>  </td>
        <td><%=e.getBirthday()%>
        <td><a href="<%=request.getContextPath()%>/model2/empDetail.do?emp_no=<%=e.getEmpno()%>">상세</a></td>
    </tr>
    <%}%>

    </tbody>
</table>
</body>
</html>
