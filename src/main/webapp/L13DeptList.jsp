<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mysql.cj.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%!
    public static final String URL = "jdbc:mysql://localhost:3306/employees";
    public static final String USER = "root";
    public static final String PASS = "mysql";
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    // 왜 전역변수로 선언하는가. JSP 페이지 내에서 반복적으로 사용되는 값을 관리하려고
%>

<html>
<head>
    <title> 부서 리스트 </title>   <%-- jsp로 불러와보기--%>
</head>

<body>
<%
    Class.forName(driver);
    String sql = "SELECT * FROM departments";
    List<Map<String, String>> deptList = null;

    try (
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    ) {
        deptList = new ArrayList<>();

        while (rs.next()) {
            String deptNo = rs.getString("dept_no");
            String deptName = rs.getString("dept_name");
            Map<String, String> dept = new HashMap<>();
            dept.put("dept_no", deptNo); // map.put(K key, V value);
            dept.put("dept_name", deptName);

            deptList.add(dept);
        }

    } catch (Exception e) {
        //throw new RuntimeException(e); //접속 문제가 있다면 500 에러 발생
        response.sendError(500);
        e.printStackTrace();
        return;
    }
%>  <%--여기까지 부서 내용 가져오는 java 코드--%>

<h1>부서리스트</h1>

<table>
    <thead>
    <tr>
        <th>부서번호</th>
        <th>부서이름</th>
        <th>부서상세(servlet+jsp)</th>
    </tr>
    </thead>

    <tbody>
    <% for(Map<String,String> dept : deptList){ %>
    <%-- deptList에서 요소들이 Map 하나하나를 꺼내서 HTML구조를 만들기 --%>
    <tr >
        <td><%=dept.get("dept_no")%></td>
        <td><%=dept.get("dept_name")%></td>
        <td>
            <a href='L14DeptDetail.do?dept_no=<%=dept.get("dept_no")%>'> 부서상세</a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
