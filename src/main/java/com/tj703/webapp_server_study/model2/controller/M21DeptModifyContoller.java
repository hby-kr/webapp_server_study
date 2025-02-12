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

@WebServlet ("/model2/deptModify.do")
public class M21DeptModifyContoller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 파라미터 받기
        String deptNoStr = req.getParameter("dept_no");

        // DB에서 부서정보 불러오기
        M16DeptDao dao = null;
        M17deptDto dept = null;
        try {
            dao = new M16DeptDaoImp(); // dao 객체 생성
            dept = dao.findById(deptNoStr); // 부서정보 하나 받기 메서드 호출

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } finally {
            if (dao != null) {
                dao.close();
            }
        }

        // 부서정보 받아왔으면, 그 자료를 포워딩
        if (dept == null) { // 실패면
            resp.sendRedirect(req.getContextPath() + "/model2/deptList.do");
        } else { // 성공이면, db자료 포워딩해야지
            req.setAttribute("dept", dept);
            req.getRequestDispatcher("/WEB-INF/views/dept/deptModify.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Post로 받은 매개변수부터 관리
        String deptNoStr = req.getParameter("dept_no");
        String deptName = req.getParameter("dept_name");

        // update 작업 수행
        M16DeptDao dao = null;
        M17deptDto dept = null;
        int update = 0;
        try {
            dept = new M17deptDto();
            dept.setDeptNoStr(deptNoStr);
            dept.setDeptName(deptName);
            dao = new M16DeptDaoImp();
            update = dao.update(dept);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }

        // 리다이렉트
        if (update > 0) {
            resp.sendRedirect(req.getContextPath() + "/model2/deptList.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/model2/deptModify.do?dept_no=" + deptNoStr);
        }

    }
}
