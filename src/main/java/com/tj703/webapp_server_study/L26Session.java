package com.tj703.webapp_server_study;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/setSession.do")
public class L26Session extends HttpServlet {
    /*
    >> 세션Session

    (첫 요청과 클라이언트)
     session은 서버에 저장되는 객체임
     요청하는 클라이언트에 대응해서 생성되는 세션 객체
     클라이언트가 서버에 요청하면,
     그 요청이 최초 요청일 때 톰캣이 JSESSIONID라는 "쿠키"를 만들고
     JSESSIONID(라는 키)의 값(value)으로 고유값을 만들어 (쿠키에 실어) 배포한다.
     세션 데이터는 서버에서 관리되고 클라이언트에는 세션 ID만 전달됩니다. 이 세션 ID는 일반적으로 쿠키로 클라이언트에 저장.
     JSESSIONID이 가지는 고유값은 (그 세션이 유효한 만료시점까지만) 클라이언트의 고유번호가 된다.

    (서버에서는)
     JSESSIONID의 값을 키로 가지는 Session 객체를 서버에 생성한다.
     이 때 세션은 일반적으로 Map<String, Object> 형태로 저장

    (이미 접속해던 = JSESSIONID를 가진 클라이언트가 다시 요청할 때)
     클라이언트가 서버에 요청하면, 함께 보낸 쿠키 내에 JSESSIONID라는 키의 값(고유값)을 가지고
     서버 내에 같은 값을 가지는 세션 객체를 찾아서(또는 최초요청시 생성하여)
     동적리소스(서블릿)에 요청정보와 함께 보내어 처리하게 한다.

    - 세션은 사용자가 웹사이트와 상호작용하는 동안만 서버에서 정보를 저장하는 방법 (서버에 저장)
    - 사용자가 웹사이트를 떠나면 사라짐. 즉 세션은 각각의 사용자가 웹사이트에서 활동하는 동안만 유지.
    - 사용자가 페이지를 이동할 때마다 서버는 해당 세션 정보를 확인하여, 사용자가 계속 로그인된 상태로 활동할 수 있게 해줌
    - 세션 ID(JSESSIONID): 서버는 사용자를 구별하기 위해 세션 ID라는 고유한 값을 생성하고, 이 값은 쿠키나 URL을 통해 브라우저와 서버 간에 전달됩니다.
    - 만료 시점: 사용자가 사이트를 떠나거나 세션이 일정 시간 동안 활동이 없으면 세션은 만료. (기본값 30분)
    - 세션 활용: 로그인 세션, 장바구니, 사용자 상태 추적

HttpSession의 주요 메서드는
    세션 데이터를 저장하거나, 읽거나, 세션의 수명을 관리하는 데 도움을 줍니다.
    1) 세션 데이터 관리:
        setAttribute(String name, Object value);    세션에 데이터를 저장합니다.
        getAttribute(String name);  세션에서 특정 이름의 데이터(무엇이 올지 모름)를 가져옵니다.
            String username = (String) session.getAttribute("username"); // 그래서 명시형변환 시켜주는 것도 필요
        removeAttribute(String name);   세션에서 특정 이름의 데이터를 제거
    2) 세션 정보 확인 및 관리:
        getId()     세션의 고유한 ID를 반환
        getCreationTime()   세션이 생성된 시간을 밀리초 단위로 반환
        getLastAccessedTime()   세션이 마지막으로 접근된 시간을 밀리초 단위로 반환
        getMaxInactiveInterval()    세션이 유효한 최대 시간(초 단위)을 반환
        setMaxInactiveInterval(int interval)    세션이 유효한 최대 시간을 설정합니다. interval은 초 단위로 지정
    3) 세션 유효성 관리:
        invalidate()    세션을 무효화하여 해당 세션의 모든 데이터를 삭제
    4) 세션 상태 확인:
        isNew()     세션이 새로 생성된 세션인지 여부를 확인

     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 사용자의 요청을 받았기 때문에, 최초이면 new세션이 만들어졌고, 최초가 아니면 기존 세션이 준비되어 있다.
        HttpSession session = req.getSession(); // 톰캣이 클라이언트와 대응되게 생성한 세션 객체를 가져오기

        // 그 세션의 여러 값들을 추가로 설정
        // setAttribute(String name, Object value)
        session.setAttribute("email", "develdkfsd@sdfsf.com");
        session.setAttribute("name", "경만");
        session.setAttribute("married", true);
        session.setAttribute("pw", 1234);

        // 페에지의 컨텐츠를 출력하는 것 뿐.
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1> session에 이메일,이름,결혼여부,비번 저장 완료!</h1>");
        out.println("<a href='./index.jsp'>메인페이지로 이동</a>");
    }
}
