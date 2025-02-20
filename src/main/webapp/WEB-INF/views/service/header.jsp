<%@ page import="com.tj703.webapp_server_study.model2_service.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
  Object loginUserObj = session.getAttribute("loginUser");
  Object msgObj = session.getAttribute("msg");
%>
<%
  if(msgObj != null) {
    session.removeAttribute("msg");%>
    <script>alert("<%=msgObj%>");</script>
<%}%>


<header style="display: flex; justify-content: space-between; align-items: center; background: cornsilk; border-bottom: 1px solid black; position: fixed; left: 0; right: 0;">
  <h3><a href="<%=request.getContextPath()%>/">홈</a></h3>

  <% if (loginUserObj != null) {  // 로그인 되었다면,
    UserDto loginUser = (UserDto) loginUserObj; // null 일 가능성있음. 만약 null이면 오류뜸.
  %>
  <div>
    <span> <%=loginUser.getEmail()%>님(<%=loginUser.getUserId()%>) 로그인중-</span>
    <a href="<%=request.getContextPath()%>/service/logout.do"><b>로그아웃</b></a>
  </div>
  <%}%>
</header>
