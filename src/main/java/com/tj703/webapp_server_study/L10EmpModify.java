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


@WebServlet("/empModify.do")
public class L10EmpModify extends HttpServlet {
// 본격 수정을 위한 페이지.  여긴 doGet, doPost가 모두 있는데
// doGet은 db자료 불러와 form요소를 보여주고
// doPost는 form요소로 제출된 데이터를 처리한다.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 파라미터 처리
        String empNoStr = req.getParameter("emp_no");

        // model 작업
        String sql = "select * from employees where emp_no = ?";
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<String, Object> emp = null;
        try {
            int empNo = Integer.parseInt(empNoStr);
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, empNo);
            rs = ps.executeQuery();

            if (rs.next()) { // 안에 데이터가 있으면 객체를 만들겠다.
                emp = new HashMap<>();
                emp.put("emp_no", rs.getInt("emp_no"));
                emp.put("first_name", rs.getString("first_name"));
                emp.put("last_name", rs.getString("last_name"));
                emp.put("gender", rs.getString("gender"));
                emp.put("birth_date", rs.getString("birth_date"));
                emp.put("hire_date", rs.getString("hire_date"));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(400); // 400 = 있어야할 파라미터가 없다.
            return;
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        }


        // 뷰 작업
        if (emp != null) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h1> 사원 수정</h1>");
            out.println("<form action='empModify.do' method='post'>");
            out.println("<p><label> 사원 번호 : "+ emp.get("emp_no")+ "<input type='hidden' value ='" + emp.get("emp_no") + "' name='emp_no'></label></p>");
            out.println("<p><label> 사원 이름 : <input value ='" + emp.get("first_name") + "' name='first_name'></label></p>");
            out.println("<p><label> 사원 성씨 : <input value ='" + emp.get("last_name") + "' name='last_name'></label></p>");

            out.println("<p><label> 남성 : <input  value ='M' " + ( (emp.get("gender").equals("M"))? "checked":"" ) +" type='radio' name='gender'></label>");
            out.println("<label> 여성 : <input value ='F' " +( (emp.get("gender").equals("F"))? "checked":"" ) +" type='radio' name='gender'></label></p>");

            out.println("<p><label> 사원 생일 : <input type='date' value ='" + emp.get("birth_date") + "' name='birth_date'></label></p>");
            out.println("<p><label> 사원 입사일 : <input type='date' value ='" + emp.get("hire_date") + "' name='hire_date'></label></p>");
            out.println("<p><button type = 'reset'>초기화</button> &nbsp; <button>수정제출</button></p>");
            out.println("<p> <a href='empRomoveAction.do?emp_no="+ emp.get("emp_no") +"'> 사원삭제</a></p>");
            out.println("</form>");
        } else {
            resp.sendRedirect("./empCRUDList.do");
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // form으로 받은 자료 처리하기.
        //   1. 파라미터 처리
        //   2. form양식데이터 처리 (JDBC)
        //   3. 리다이렉트


        //   1. 파라미터 처리
        String empNoStr = req.getParameter("emp_no");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String gender = req.getParameter("gender");
        String birthDate = req.getParameter("birth_date");
        String hireDate = req.getParameter("hire_date");
        // 모든 파라미터는 모두 문자열이다.


        //   2. form양식데이터 처리 (JDBC)
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";

        Connection conn = null;
        PreparedStatement ps = null;
        int update = 0; // 성공시 1

        String sql = "update employees SET first_name=?, last_name=?, gender=?, birth_date=?, hire_date=?  where emp_no = ?";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, gender);
            ps.setString(4, birthDate);
            ps.setString(5, hireDate);
            ps.setInt(6, Integer.parseInt(empNoStr));
            update = ps.executeUpdate(); // 성공1 실패0
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 3. 리다이렉트
        if (update > 0) { // 성공
            resp.sendRedirect("./empDetail.do?emp_no="+empNoStr);
        } else {
            resp.sendRedirect("./empModify.do?emp_no="+empNoStr);
        }
    }
}
