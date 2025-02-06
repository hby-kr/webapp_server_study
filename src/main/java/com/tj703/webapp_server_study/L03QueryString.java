package com.tj703.webapp_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/queryStringStrudy.do")
public class L03QueryString extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();
        System.out.println(queryString);
        // name=abce&age=30... 을 어떻게 나눌 것인가.

//        String[] params = queryString.split("&");
//        Arrays.stream(params)
//                .map(param -> param.split("="));

        String [] params=queryString.split("&");
        //  {name=a,age=30,id=33,gender=m}
        Map paramMap=new HashMap();
        for(String param:params){
            String [] p=param.split("=");
            paramMap.put(p[0],p[1]);
        }

        String name =req.getParameter("name"); // 쿼리스트링에서 파라미터만 빼는 것.
        String ageStr = req.getParameter("age");
        // 파라미터는 문자열과 숫자를 구분하지 않고, 모두 문자열로 취급
        int age = Integer.parseInt(ageStr); // 그래서 내가 사용할 타입으로 숫자로 변환

        resp.setContentType("text/html"); // 응답의 타입 설정
        PrintWriter out = resp.getWriter();
        out.write("<ul>");
        out.write("<li> queryString :"+queryString+"</li>");
        out.write("<li> name :"+name+"</li>");
        out.write("<li> age :"+age+"</li>");
        out.write("<li> name :"+paramMap.get("name")+"</li>");
        out.write("<li> age :"+paramMap.get("age")+"</li>");
        out.write("</ul>");

    }
}
