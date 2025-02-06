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

// 액션 페이지, 액션리소스. 회원가입 폼을 받아서 creat만 하고 리다이렉트한다.
@WebServlet("/empSignupAction.do")
public class L08empSignupAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // emp를 등록하는 action 페이지
        //   1. 양식데이터 받기
        //   2. 양식데이터 처리 (JDBC)
        //   3. redirect로 페이지 이동


        //   1. 양식데이터 받기
        String empNoStr = req.getParameter("emp_no");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String gender = req.getParameter("gender");
        String birthDate = req.getParameter("birth_date");
        String hireDate = req.getParameter("hire_date");
        // 모든 파라미터는 모두 문자열이다.


        //   2. 양식데이터 처리 (JDBC)
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";
        String sql = "insert into employees (emp_no, birth_date, first_name, last_name, gender, hire_date)" +
                " values (?,?,?,?,?,?)";

        int insert = 0; // insert하고 나면 결과를 가져 오기 때문.  DML(inser,update,delete의 결과는 0,1로 보여준다)

        try {
            Class.forName(driver); // 얘는 close()가 없어서 밖에 씀. 그런데 얘는 try-catch문에 넣어줘야함
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 굳이 auto close를 써보려고
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);)
        {
            int empNo = Integer.parseInt(empNoStr);

            ps.setInt(1, empNo);
            ps.setString(2, birthDate);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, gender);
            ps.setString(6, hireDate);
            insert = ps.executeUpdate(); // executeUpdate는 dml을 실행하는 함수
            // 오류가 발생하는 이유
            // 1. NumberFormatException :
            // 2. sQL
            // 3. empno 중복, 타입이 맞지 않을 때, 길이가 넘었을 때  => 양식데이터로 확인하고 다시 가입시도시키면 됨.
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        }


        //   3. redirect로 페이지 이동
        // 오류가 발생하거나, 등록이 안되면 insert=0이다. -> 다시 양식페이지로 이동. empSignup.html로 이동
        // insert=1 이면 등록 성공. empList (empCRUD.do)로 이동
        if (insert > 0) {
            resp.sendRedirect("/empCRUD.do");
        } else {
            resp.sendRedirect("/empSignup.html");
        }


    }
}
