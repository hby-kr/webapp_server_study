package com.tj703.webapp_server_study.model2.controller;

import com.tj703.webapp_server_study.model2.dao.L16EmpDao;
import com.tj703.webapp_server_study.model2.dao.L16EmpDaoImp;
import com.tj703.webapp_server_study.model2.dto.L17EmpDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/model2/empModify.do")
public class L21EmpModifyController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // post로 받았으니 매개변수 관리해야지
        String empNoStr = req.getParameter("emp_no");

        L17EmpDto emp = null;
        L16EmpDao empDao = null;
        int update = 0;
        String successUrl = req.getContextPath() + "/model2/empList.do";
        String errorUrl = req.getContextPath() + "/model2/empModify.do?emp_no=" + empNoStr;

        try {
            emp = new L17EmpDto();
            emp.setEmpno(Integer.parseInt(empNoStr));
            emp.setFirstname(req.getParameter("first_name"));
            emp.setLastname(req.getParameter("last_name"));
            emp.setGender(req.getParameter("gender"));
            emp.setBirthday(req.getParameter("birth_date"));
            emp.setHiredate(req.getParameter("hire_date"));
            empDao = new L16EmpDaoImp();
            update = empDao.update(emp); // 성공시 1
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 리다이렉트
        if (update > 0) {
            resp.sendRedirect(successUrl);
        } else {
            resp.sendRedirect(errorUrl);
        }

    }
}
