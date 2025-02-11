package com.tj703.webapp_server_study.model2.controller;

import com.tj703.webapp_server_study.model2.dao.L16EmpDao;
import com.tj703.webapp_server_study.model2.dao.L16EmpDaoImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/model2/empDelete.do")
public class M22EmpDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 매개변수 관리
        String empNoStr = req.getParameter("emp_no");

        // delete 작업
        L16EmpDao empDao = null;
        int delete = 0;

        try {
            int empNo = Integer.parseInt(empNoStr);
            empDao = new L16EmpDaoImp();
            delete = empDao.deleteById(empNo);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 리다이렉트
        if (delete > 0) {
            resp.sendRedirect(req.getContextPath() + "/model2/empList.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/model2/empModify.do?emp_no=" + empNoStr);
        }
    }
}
