package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;


@WebServlet("/empDetail.do")
public class L09EmpDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String empNoStr = req.getParameter("emp_no");
        String sql = "select * from employees where emp_no = ?";

        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";
        // model 1 : 동적 페이지에서 접속하고 출력하는 모든 코드가 모여있는 디자인
        //          모든 비즈니스 로직과 뷰(출력) 코드가 한 곳에 모여 있는 디자인 패턴
        // model 2 :  MVC (Model-View-Controller) 패턴  / Model = dao , View = html, Controller = Servlet
        //      Model (모델): =dao / 데이터를 가져오거나 데이터를 저장하는 역할
        //      View (뷰): =html / 사용자에게 출력을 제공하는 컴포넌트. 모델로부터 데이터를 받아서 사용자에게 보여줌.
        //      Model (모델): =Servlet / 사용자의 요청을 처리하고, 필요한 모델과 뷰를 연결하는 역할

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<String, Object> emp = null;  // 무엇을 받을지 모르기 때문에 Object로 통으로 받는 것임.. 개선하는 방법은 DTO를 만드는 것.

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

        if (emp != null) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h1> 사원 상세</h1>");
            out.println("<p> 사원 번호 : " + emp.get("emp_no") + "</p>");
            out.println("<p> 사원 이름 : " + emp.get("first_name") + "</p>");
            out.println("<p> 사원 성씨 : " + emp.get("last_name") + "</p>");
            out.println("<p> 사원 성별 : " + emp.get("gender") + "</p>");
            out.println("<p> 사원 생일 : " + emp.get("birth_date") + "</p>");
            out.println("<p> 사원 입사일 : " + emp.get("hire_date") + "</p>");
            out.println("<p><a href='./empModify.do?emp_no=" + emp.get("emp_no") + "'>수정하기</a></p>");
        }

    }
}
