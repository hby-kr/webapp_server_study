<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>광고</title>
</head>
<body>
<h1>광고</h1>

<p>광고광고광고광고광고광고광고광고광고광고광고광고</p>
<p>연습연습연습연습연습연습연습연습연습연습연습연습</p>
<p>광고광고광고광고광고광고광고광고광고광고광고광고</p>

<%-- 광고 한번 보여주고, 안보고 싶으면 클릭 유도. form문으로 만들기--%>
<form action="./M25setBannerCookie.do" name="bannerForm">
    <p><label>
        광고 그만 보고 싶으면 체크하라<input type="checkbox" name="isBannerCookie" value="1" checked />
    </label></p>
    <p>
        <button type="submit">제출</button>
    </p>
</form>

</body>
</html>
