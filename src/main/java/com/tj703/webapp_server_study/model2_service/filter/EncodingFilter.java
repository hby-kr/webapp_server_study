package com.tj703.webapp_server_study.model2_service.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/*
Filter 클래스는 웹 애플리케이션에서 요청(request)과 응답(response)을 가로채어 처리할 수 있도록 하는 인터페이스입니다.
Filter는 보통 서블릿 앞뒤에서 동작하며, 주로 요청을 처리하거나 응답을 수정하는 데 사용.
Filter는 요청이 서블릿에 도달하기 전에, 혹은 서블릿이 응답을 반환한 후, 그 중간에서 작업을 할 수 있는 기회를 제공합니다.
예를 들어, 로그 기록, 인증, 권한 검사, 응답 내용 수정 등의 작업을 할 때 유용하게 사용

주요 특징:
1) 요청 가로채기: 클라이언트가 보낸 요청이 서블릿에 도달하기 전에 Filter가 그 요청을 처리할 수 있습니다.
2) 응답 가로채기: 서블릿이 생성한 응답이 클라이언트에게 전송되기 전에 Filter가 그 응답을 처리할 수 있습니다.
3) 체인 처리: 여러 개의 필터가 있을 때, 필터들은 체인처럼 연결되어 순차적으로 처리됩니다.

WebFilter는
- 특정 URL에만 필터를 적용: 필터는 모든 요청에 대해 적용할 수도 있지만, 원하는 URL에만 적용되도록 설정할 수도 있습니다. 예를 들어, 로그인 페이지나 특정 API에만 적용할 수 있습니다.
- 여러 필터를 함께 사용할 수 있음: 여러 가지 필터를 설정해서 순서대로 적용하게 할 수 있습니다. 각 필터는 특정 작업을 하고, 그 다음 필터로 요청을 넘깁니다.
- 웹 필터를 사용해 "모든 요청에 대해 로그를 남긴다"거나, "특정 페이지에 접근하려면 인증이 필요하다"는 등의 작업을 쉽게 할 수 있습니다.

 */

// 클라이언트와 서블릿 사이에서 동작함.
// 쉽게 말해, 웹 서버와 사용자의 요청/응답 사이에서 중간에 끼어들어 원하는 작업을 할 수 있는 기능을 제공합니다.
@WebFilter ("/service/*") // service/ 이후 모든 페이지에서 필터가 적용된다. 모든 요청의 서블릿을 실행하기 전에 EncodingFilter 필터를 실행하라.
public class EncodingFilter implements Filter {
// 이 인코딩 필터는 웹 애플리케이션에서 문자 인코딩을 일관되게 설정하여 요청과 응답에서 발생할 수 있는 문자 깨짐을 방지하는 필터작업


    // 매개변수인 ServletRequest는 서블릿의 매개변수인 HttpServletRequest의 부모 인터페이스다.
    // 달리말하면 ServletRequest을 HttpServletRequest 상속하여 더 확장된 것/
    // ServletRequest: HTTP가 아닌 다른 프로토콜을 지원하는 요청도 처리할 수 있는 기본적인 요청 객체입니다.
    // 즉, HTTP뿐만 아니라 다른 프로토콜을 지원하는 서블릿에서도 사용할 수 있는 범용적인 요청 객체
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        System.out.println("중간에 EncodingFilter 필터가 적용되고 있음");
        filterChain.doFilter(req, resp); // 원래 가던 길 가라.
        // response.sendRedirect() // 가던길 말고 다른 곳 가라.
    }
}
// 같은 기능으로 스프링에는 인터셉터가 있음
