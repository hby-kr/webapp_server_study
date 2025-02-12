package com.tj703.webapp_server_study.model2.controller;

import com.tj703.webapp_server_study.model2.dao.M16DeptDao;
import com.tj703.webapp_server_study.model2.dao.M16DeptDaoImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet ("/model2/deptDelete.do")
public class M22DeptDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get으로 받은 매개변수 처리
        String deptNo = req.getParameter("dept_no");

        // delete 작업시작
        M16DeptDao dao = null;
        int delete = 0;
        try{
            dao = new M16DeptDaoImp();
            delete = dao.deleteById(deptNo);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 리다이렉트
        if (delete > 0) {
            resp.sendRedirect(req.getContextPath() + "/model2/deptList.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/model2/deptModify.do?dept_no" + deptNo);
        }
    }
}
