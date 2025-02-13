package com.tj703.webapp_server_study.model2.controller;

import com.tj703.webapp_server_study.model2.dao.M16DeptDao;
import com.tj703.webapp_server_study.model2.dao.M16DeptDaoImp;
import com.tj703.webapp_server_study.model2.dto.M17deptDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet ("/model2/deptRegister.do")
public class M19DeptRegisterController extends HttpServlet {

    // get 매개변수는 받은거 없으니까, 그냥 포워드 시키기
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/dept/deptRegister.jsp").forward(req, resp);
    }

    // post로 register form요소를 받음
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 먼저 form요소(post) 받기
        String deptNo = req.getParameter("dept_no");
        if (deptNo == null || deptNo.length() > 4) {
            resp.sendError(404);
            return; // 오류면, 함수전체를 여기서 종료.
        }

        // DB로 create 작업
        M16DeptDao dao = null;
        int insert = 0;
        try {
            M17deptDto dept = new M17deptDto();
            dept.setDeptNoStr(deptNo);
            dept.setDeptName(req.getParameter("dept_name"));

            dao = new M16DeptDaoImp();
            insert = dao.insert(dept);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }

        // 리다이렉트
        if (insert > 0) {
            resp.sendRedirect(req.getContextPath() + "/model2/deptList.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/model2/deptRegister.do");
        }
    }
}
