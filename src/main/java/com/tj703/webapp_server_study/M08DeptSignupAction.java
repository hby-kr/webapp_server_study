package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

// 액션 페이지, 액션리소스
@WebServlet("/deptSignupAction.do") // 절대경로 설정
public class M08DeptSignupAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // dept를 등록하는 action 페이지
        //   1. 양식데이터 받기
        //   2. 양식데이터 처리 (JDBC)
        //   3. redirect로 페이지 이동

        //   1. 양식데이터 받기
        String deptNo = req.getParameter("dept_no");
        String deptName = req.getParameter("dept_name");
        // 모든 파라미터는 모두 문자열이다.


        //   2. 양식데이터 처리 (JDBC)
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";
        String sql = "insert into departments (dept_no, dept_name) values(?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        int insert = 0; // 성공하면 1

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);

            ps.setString(1, deptNo);
            ps.setString(2, deptName);
            insert = ps.executeUpdate(); // 성공하면 1로 바뀜

        } catch (Exception e) {
            e.printStackTrace();
            // 실패하면 리다이렉트 할 거임
            // e.printStackTrace();
            // resp.sendError(500);
        } finally {
            if (ps != null) {try { ps.close();} catch (SQLException e) {}}
            if (conn != null) {try { conn.close();} catch (SQLException e) {}}
        }

        //   3. redirect로 페이지 이동
        if (insert > 0) {
            resp.sendRedirect("./deptCRUD.do"); // 등록 성공하면 리스트 페이지로.
        } else {
            resp.sendRedirect("./deptSignup.html"); // 등록 실패하면 다시 할 수 있게 등록페이지로.
        }

    }
}
