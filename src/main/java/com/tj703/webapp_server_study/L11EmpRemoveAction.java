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

@WebServlet("/empRomoveAction.do")
public class L11EmpRemoveAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. emp_no 파라미터 받아서 (없거나 정수가 아니면 400)
        String empNoStr = req.getParameter("emp_no");

        // 2. jdbc로 삭제
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "mysql";
        String driver = "com.mysql.cj.jdbc.Driver";

        String sql = "DELETE FROM employees WHERE emp_no = ?";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int delete = 0; // 성공1
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(empNoStr));
            delete = ps.executeUpdate();
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (Exception e) {
            throw new RuntimeException();
        }

        // 3. 리다이렉트;  성공->list로,  실패->수정페이지로 이동
        if (delete > 0) { //성공
            resp.sendRedirect("./empCRUDList.do");
        } else {
            // 실패하면 어디로 보낼지 고민
            resp.sendRedirect("./empModify.do?emp_no=" + empNoStr);
        }

        


    }
}
