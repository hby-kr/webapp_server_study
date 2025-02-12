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
import java.util.List;

@WebServlet("/model2/empList.do") // 절대경로 맵핑. 즉 contextpath()/model2/empList.do 가 있다고 맵핑한 것.
public class L18EmpListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. back 작업 (dao, dto 작업 모두 완료되었으므로, 그것을 활용하여 DB에서 데이터를 불러오는 작업)
        L16EmpDao empDao = null;
        List<L17EmpDto> empList = null;
        try {
            empDao = new L16EmpDaoImp();
            empList = empDao.findAll();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            // List에서 오류가 발생할 이유는 db문제이므로 500으로 서비스를 멈춘다.
            e.printStackTrace();
            resp.sendError(500);
            return;
            // 400: 클라이언트 요청 오류
            // 404: 요청한 리소스 없음
            // 500: 서버 내부 오류
        } finally { // 무조건 한번은 실행한다.
            if (empDao != null) {
                empDao.close();
            }

        }

        // DB는 empList로 불러왔는데, 그 이후 화면에 뿌려주는 방법은 뷰 템플릿 엔진으로 하려고 하니까... 포워딩


        // 2. foward 하기
        req.setAttribute("empList", empList); // 포워딩할 페이지에 파일 건내버리기
        // "empList"라는 이름으로 위에서 작업한 empList를 보내자

        req.getRequestDispatcher("/WEB-INF/views/emps.jsp").forward(req, resp); // 이로써 뷰 템플릿 엔진과 연결되었고, 포워드 됨.
        // 포워드를 받을 JSP 파일은 webapp/WEB-INF 폴더 안에 위치하는데,
        // 이 폴더는 일반적으로 클라이언트에서 직접 접근할 수 없는 경로입니다.
        // webapp/WEB-INF 폴더는 또한 web.xml과 같은 배포 서술자(Deployment Descriptor, 개발자가 배포에 대한 설정을 적어놓은 문서)파일이 위치하는 곳
        // WEB-INF 폴더는 서버 내부에서만 접근 가능하며, 외부 사용자가 직접 URL을 통해 접근할 수 없음
        // == private지정자처럼 내부적으로만 접근 가능

        // jsp는 java와 동일한 코드를 실행할 수 있고, 응답과 요청으로 모두 위임하기 때문에 보안에 취약하다.

        // 왜 jsp 문서를 WEB-IN에 두어 숨겨두나? 뷰템플릿 엔진(jsp)도 백엔드 언어(java)이기 때문에 외부에서 접근할 수 없는 경로에 둠.
        // (jsp는 안에서 java언어 다 돌릴 수 있는 편리함과 위험함을 모두 가지고 있다)
    }
}
