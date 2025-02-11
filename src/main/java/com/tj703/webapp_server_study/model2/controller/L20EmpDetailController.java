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

@WebServlet ("/model2/empDetail.do")
public class L20EmpDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 파라미터 받기
        String empNoStr = req.getParameter("emp_no"); // 파라미터는 모두 String

        // DB접속해서 뿌려줄 데이터 가져오기
        L16EmpDao dao = null;
        L17EmpDto emp = null;

        try {
            int empNo = Integer.parseInt(empNoStr);
            dao = new L16EmpDaoImp();
            emp = dao.findById(empNo); // 한 사람의 dto

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        }

        // 가져온 DB를 보여줄 View로 포워드
        if (emp == null) { // 조회와 동시에 삭제된 경우 -> 리스트로 이동
            resp.sendRedirect("./empDetail.do");
        } else { // 성공했으면
            req.setAttribute("emp", emp);
            req.getRequestDispatcher("/WEB-INF/views/empDetail.jsp").forward(req, resp);
        }


    }
}
