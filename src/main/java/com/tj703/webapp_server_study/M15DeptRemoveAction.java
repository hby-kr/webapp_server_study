package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/deptRomoveAction.do")
public class M15DeptRemoveAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 1. dept_no 파라미터 받아서 (없거나 정수가 아니면 400)
        String deptNo = req.getParameter("dept_no");

        // 2. jdbc로 삭제
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";

        String sql = "DELETE FROM departments WHERE dept_no = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int delete = 0; // 성공1

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptNo);
            delete = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. 리다이렉트;  성공->list로,  실패->수정페이지로 이동
        if (delete > 0) { //성공
            resp.sendRedirect("./deptCRUD.do");
        } else {
            resp.sendRedirect("./deptModify.do?dept_no=" + deptNo);
        }
    }
}
