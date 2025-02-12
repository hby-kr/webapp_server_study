package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/deptCRUD.do")
public class M07DeptCrudList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // JDBC로 Db에서 불러오기
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";
        String sql = "select * from departments order by dept_no";

        List<Map> empList = null;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sql);

            empList = new ArrayList<>();

            while (rs.next()) {
                Map emp = new HashMap();
                emp.put("dept_no", rs.getString("dept_no"));
                emp.put("dept_name", rs.getString("dept_name"));
                empList.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {try { rs.close();} catch (SQLException e) {}}
            if (stmt != null) {try { stmt.close();} catch (SQLException e) {}}
            if (conn != null) {try { conn.close();} catch (SQLException e) {}}
        }

        // 응답할 동적리소스 생성하기
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<h1> 부서사원 관리 페이지 </h1>");
        out.println("<hr><br>");
        out.println("<h2> 부서 리스트 </h2>");
        out.println("<a href='./deptSignup.html'> 부서 등록할 수 있음 (페이지이동) </a>");
        out.println("<br><hr><br>");

        out.println("<table>");
        out.println("<tr>");
        out.println("<th>부서번호</th>");
        out.println("<th>부서이름</th>");
        out.println("<th>수정하기</th>");
        out.println("</tr>");

        for (Map emp : empList) {
            out.println("<tr>");
            out.println("<td>" + emp.get("dept_no") +"</td>");
            out.println("<td>" + emp.get("dept_name") +"</td>");
            out.println("<td><a href='./deptModify.do?dept_no=" + emp.get("dept_no")+ "'> 수정 </a> </td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br><hr>");
        out.println("</html>");
    }
}
