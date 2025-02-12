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
public class L22EmpDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // GET으로 받은 매개변수 관리하기
        String empNoStr = req.getParameter("emp_no");

        // delete 작업 시작
        L16EmpDao empDao = null;
        int delete = 0; // 삭제 확인을 위한 변수

        try {
            int empNo = Integer.parseInt(empNoStr);
            empDao = new L16EmpDaoImp();
            delete = empDao.deleteById(empNo);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (Exception e) { // 1. Db접속오류, 2. 쿼리가 잘못? 3. 과다접속
            e.printStackTrace();
            // resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // return; // 500에러 안띄우고 그냥 디테일로 보내자
        } finally { // 무조건 한번은 실행한다.
            if (empDao != null) {
                empDao.close();
            }

        }

        // 리다이렉트
        if (delete > 0) {
            resp.sendRedirect(req.getContextPath() + "/model2/empList.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/model2/empDetail.do?emp_no=" + empNoStr);
        }
    }
}
