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

@WebServlet("/model2/empRegister.do")
public class L19EmpResiterController extends HttpServlet {

    // get -> 등록 받기 폼 (포워드 방식)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/empRegister.jsp").forward(req, resp);
        // forward(req, resp) 메서드를 사용하여 포워딩을 하면, req와 resp객체에 담긴 모든 정보가 포워딩된 서블릿이나 JSP로 그대로 전달.
        // request 객체가 그대로 포워딩되므로, 원래의 요청에 담긴 데이터 (예: 파라미터, 속성 등)는 포워딩된 서블릿이나 JSP에서도 그대로 접근 가능
    }

    // post -> 등록 액션
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // doPost는우선 form태그로부터 POSt요청받은 파라미터부터 먼저 처리해야.
        L17EmpDto emp = new L17EmpDto(); // 받을 객체 만들고.
        L16EmpDao empDao = null;
        int insert = 0;

        try {
            emp.setEmpno(Integer.parseInt(req.getParameter("emp_no")));
            emp.setFirstname(req.getParameter("first_name"));
            emp.setLastname(req.getParameter("last_name"));
            emp.setBirthday(req.getParameter("birth_date"));
            emp.setGender(req.getParameter("gender"));
            emp.setHiredate(req.getParameter("hire_date"));

            empDao = new L16EmpDaoImp();
            insert = empDao.insert(emp);// L17EmpDto emp 를 인수로 넣으면, 알아서 안 쪽 값을 알아서 찾아다 적절한 곳에 넣어줌.
            // 성공하면 1
        } catch (Exception e) {
            // 가능한 오류:  DB가 생성되지 않은 경우, 파라미터가 잘못된 경우, ...
            e.printStackTrace();
        }

        // 리다이렉트
        if (insert > 0) { // 성공시
            resp.sendRedirect(req.getContextPath() + "/model2/empList.do");
        } else { // 실패시  (pk 중복, 빈값 등등)
            resp.sendRedirect(req.getContextPath() + "/model2/empRegister.do");
        }


    }
}
