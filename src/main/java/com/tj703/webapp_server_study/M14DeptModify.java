package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

// 각각의 리스트 각각의 부서에
@WebServlet("/deptModify.do")  // 절대경로
public class M14DeptModify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String deptNo = req.getParameter("dept_no");  // 일단 doGet에 파라미터 값부터 받기

        // 1. JDBC 작업, db값 불러오기
        String sql = "select * from departments where dept_no = ?";

        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<String, Object> dept = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptNo);
            rs = ps.executeQuery();

            if (rs.next()) { // 안에 데이터가 있으면 객체를 만들겠다.
                dept = new HashMap<>();
                dept.put("dept_no", rs.getInt("dept_no"));
                dept.put("dept_name", rs.getString("dept_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        }

        // 2. 동적 리소스 생성하기
        if (dept != null) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h1> 부서 수정</h1>");

            out.println("<form action='deptModify.do' method='post'>");

            out.println("<p><label> 부서 번호 : " + dept.get("dept_no") + "<input type='hidden' value ='" + dept.get("dept_no") + "' name='dept_no'></label></p>"); // 부서번호 보여주지만 바꿀 순 없게 만들기
            out.println("<p><label> 부서 이름 : <input value ='" + dept.get("dept_name") + "' name='dept_name'></label></p>");

            out.println("<p><button type = 'reset'>초기화</button> &nbsp; <button>수정제출</button></p>");
            out.println("<p> <a href='deptRomoveAction.do?dept_no=" + dept.get("dept_no") + "'> 부서삭제</a></p>");
            out.println("</form>");
        }
    }

    // form 제출 했으니까, 그거 가지고 update해야지
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //   1. 양식데이터 받기
        String deptNo = req.getParameter("dept_no");
        String deptName = req.getParameter("dept_name");

        //   2. 양식데이터 처리 (JDBC)
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection conn = null;
        PreparedStatement ps = null;
        int update = 0; // 성공시 1

        String sql = "update departments set dept_name = ? where dept_no = ?";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptName);
            ps.setString(2, deptNo);
            update = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. 리다이렉트
        if (update > 0) {
            resp.sendRedirect("./deptCRUD.do"); // 수정 성공하면 리스트 페이지로.
        } else {
            resp.sendRedirect("./deptModify.do?dept_no=" + deptNo); // 실패하면 다시 수정페이지 머물러.
        }

    }
}