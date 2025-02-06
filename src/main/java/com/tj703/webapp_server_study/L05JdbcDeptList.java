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

@WebServlet("/jdbcDeptList.do")
public class L05JdbcDeptList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";
        String sql = "select * from departments";

        List<Map<String, Object>> deptList = null;

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password); // 접속
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            deptList = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("dept_no", rs.getString("dept_no"));
                map.put("dept_name", rs.getString("dept_name"));

                deptList.add(map);
            }


        } catch (Exception e) {
//             throw new RuntimeException(e); // throw하면 catch가 종료된다.
            // 얘보다
            resp.sendError(500);
            return;
            // 위방식을 추천.
            // 서버의 중요한 내역을 에러로 배포하면 해킹의 위험이 있다.
        }



        resp.setContentType("text/html; charset=utf-8"); // 타입 설정
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<h1> jdbc 부서 리스트</h1>");

        out.print("<table><tr><th>부서번호</th><th>이름</th></tr>");
        for (Map<String, Object> map : deptList) {
            out.print("<tr>" +
                    "<td>" + map.get("dept_no") + "</td>" +
                    "<td>" + map.get("dept_name") + "</td>");
            out.print("<td><a href:./deptDetail.do?dept_no="+ map.get("detp_no")+">상세</a></td>");
        }

        out.print("</tr>");
        out.print("</table>");

        out.print("</html>");

    }
}
