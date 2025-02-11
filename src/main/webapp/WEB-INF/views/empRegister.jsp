<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title> 사원 등록 폼</title>
</head>
<body>
<h1> 사원 등록 폼</h1>

<form method="POST" action="<%=request.getContextPath()%>/model2/empRegister.do">
    <p><label>
        사번 <input type="text" name="emp_no" value="111"/>
    </label></p>
    <p><label>
        이름 <input type="text" name="first_name" value="길동"/>
    </label></p>
    <p><label>
        성 <input type="text" name="last_name" value="홍"/>
    </label></p>
    <p><label>
        <input type="radio" name="gender" value="M" checked/>
        <input type="radio" name="gender" value="F"/>
    </label></p>
    <p><label>
        생일 <input type="date" name="birth_date" value="2000-01-01"/>
    </label></p>
    <p><label>
        입사일 <input type="date" name="hire_date" value="2025-02-11"/>
    </label></p>
    <p>
        <button type="reset">초기화</button>
        <button type="submit">제출</button>
    </p>
</form>
</body>
</html>