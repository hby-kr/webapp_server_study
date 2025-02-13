package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

import java.util.HashMap;
import java.util.Map;

@WebServlet("/L14DeptDetail.do")
public class L14DeptDetail extends HttpServlet {

    // db 연결, model 작업
    public static final String URL = "jdbc:mysql://localhost:3306/employees";
    public static final String USER = "root";
    public static final String PASS = "mysql";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection conn;
    public static PreparedStatement pstmt;
    public static ResultSet rs;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // doGet에서 가장 먼저 해야하는 것은 파라미터의 변수를 정의해주는 것.
        String deptNo = req.getParameter("dept_no");
        if (deptNo == null && deptNo.equals("")) { // 입력 안되었을 때는
            resp.sendError(400);
            return;  // 매개변수 부적합 오류
        }


        // db 작업
        Map<String, String> dept = null;
        String sql = "select * from departments where dept_no = ?";
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deptNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dept = new HashMap<>();
                dept.put("dept_no", rs.getString("dept_no"));
                dept.put("dept_name", rs.getString("dept_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 포워딩
        if (dept != null) { //조회와 동시에 삭제되지 않았거나 오류가 발생하지 않을 때 (=성공적이면)

            // view를 렌더링할 jsp 객체를 전달 = 포워딩 (model과 view를 나눠보기)
            req.setAttribute("dept", dept); // 보낼 파일을 설정하고 지정함
            //    req.setAttribute는 dept 객체를 "dept"라고 이름을 지어서 VIEW로 보내는 메서드
            req.getRequestDispatcher("L14DeptDetail.jsp").forward(req, resp); // 어디로 보낼건지,
            //    getRequestDispatcher는 지정한 JSP 페이지(L14DeptDetail.jsp)로 요청을 포워딩하는 메서드
            //    forward(req, resp)는 인수인 응답과 요청(req, resp)을, 위임함. 목적지인 "L14DeptDetail.jsp" 페이지로.
            //    현재 서블릿의 실행을 종료하고 JSP 페이지로 제어를 넘깁니다.

            // jsp는 forward 방식으로 view를 렌더링함.
            // forward 방식이란 요청과 응답을 viewv페이지(jsp)에게 위임함.
            // (jsp는 자바 코드를 가지고 있으니까, 응답까지 모두 가능하므로.)
            // cf. 최신 view template 동작 원리는 : 렌더링한 html만 반환한다.
        } else {
            resp.sendRedirect("./L13DeptList.jsp"); //status 302
        }

/*
redirect 와 forward 의 차이
    redirect : 서버 내의 다른 리소스를 요청해서 이동 -> 다른 페이지로 변화
        Redirect는 클라이언트에게 새로운 요청을 보내도록 지시하는 방식으로, URL이 변경되고 클라이언트가 다시 요청을 보냅니다.
    forward : sevlet 동적페이지가 view로 사용할 jsp 를 포함시켜서 동작
        Forward는 서버 내부에서 요청을 전달하는 방식으로, URL을 변경하지 않고 서버에서 결과를 렌더링합니다.

어떤 경우에 사용될까요?
    Forward: 같은 애플리케이션 내에서 데이터를 처리하고, 해당 데이터를 다른 JSP 페이지로 넘기면서 URL을 변경하고 싶지 않은 경우에 사용됩니다.
    예를 들어, 서블릿이 요청을 처리한 후 데이터를 JSP 페이지로 넘기기 위해 자주 사용됩니다.

    Redirect: 클라이언트에게 새로운 URL로 요청을 하도록 유도해야 할 때 사용됩니다.
    예를 들어, 사용자가 폼을 제출한 후 성공 페이지로 이동하거나, 로그인 후 다른 페이지로 이동할 때 사용됩니다.
    URL을 새로 지정하고 싶은 경우에 적합합니다.
*/
    }
}

