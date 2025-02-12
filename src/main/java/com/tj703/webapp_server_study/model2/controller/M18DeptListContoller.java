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
import java.util.List;

@WebServlet("/model2/deptList.do")
public class M18DeptListContoller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. back 작업 (dao, dto 작업 모두 완료되었으므로, 그것을 활용하여 DB에서 데이터를 불러오는 작업)
        M16DeptDao deptDao = null; // dao 작업 할 거임.
        List<M17deptDto> deptList = null; // 담을 리스트 자리만 먼저 선언
        try {
            deptDao = new M16DeptDaoImp();
            deptList = deptDao.findAll(); // 전체 리스트 넣기
        } catch (Exception e) { // 여기서 문제는 DB오류.
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } finally {
            if (deptDao != null) { // 켜있으면
                deptDao.close();  // 닫어
            }
        }

        // 2. foward 하기
        req.setAttribute("deptList", deptList); // DB로 받은 객체를 뷰템플릿엔진에게 포워드할거임. 이름지어서 보내는 것
        req.getRequestDispatcher("/WEB-INF/views/dept/deptList.jsp").forward(req, resp);
        // 이제 deptList.jsp 만들러 가야지
    }


}
