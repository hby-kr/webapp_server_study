<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>광고</title>
</head>
<body>

<h1>광고</h1>

<p>광고광고광고광고광고광고광고광고광고광고광고광고</p>


<%-- 광고 한번 보여주고, 안보고 싶으면 클릭 유도. form문으로 만들기--%>
<form action="./setBannerCookie.do" name="bannerForm">
    <p><label>
        오늘 하루 보지 않기 <input type="checkbox" name="isBannerCookie" value="1" checked />
    </label></p>
    <p>
        <button type="submit">제출</button>
    </p>
</form>

</body>
</html>
