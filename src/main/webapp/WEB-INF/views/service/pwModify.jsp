<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>유저 비밀번호 변경</title>
</head>
<body>
<%@include file="header.jsp" %>

<br><br><br><br>
<h1> 유저 비밀번호 변경 </h1>
<form method="POST" action="./pwModify.do" name="pwModifyForm">
    <p>
        변경 할 비밀번호: <label><input type="text" name="pw"></label>
    </p>
    <p><small class="msg" id="pwMsg"></small></p>
    <p>
        변경 할 비밀번호 한번 더: <label><input type="text" name="pwConfirm"></label>
    </p>
    <p><small class="msg" id="pwConfirmMsg"></small></p>
    <p>
        <button>변경하기</button>
    </p>
</form>
<script>
    const pwModifyForm = document.forms['pwModifyForm'];
    const pwMsg = document.getElementById('pwMsg');
    const pwConfirmMsg = document.getElementById('pwConfirmMsg');
    // 없으면 null로 오류가 날 수 있으므로, 이와 같이 요소(=태그, DOM객체)를 불러서 작업한다.
    // js는 타입이 불분명한 언어이기 때문에 바뀌시 않도록 const상수로 정의한다.
    /*
        <input> 요소에서 사용할 수 있는 주요 이벤트
            focus: 사용자가 <input> 요소를 클릭하거나 탭하여 포커스를 얻을 때 발생.
            blur: 사용자가 <input> 요소의 포커스를 잃을 때 발생.
            change: <input> 요소의 값이 변경되고, 포커스를 잃을 때 발생. (특히 텍스트 입력, 라디오 버튼, 체크박스 등에 사용)
            input: <input> 요소의 값이 실시간으로 변경될 때 발생. (타이핑할 때마다 발생)
            submit: <input type="submit"> 버튼을 클릭했을 때 발생.
            - addEventListener()에서 on 접두사를 사용하지 않으며, 여러 개의 이벤트 리스너를 추가
     */
    pwModifyForm.pw.oninput = function() {
        // this == pw == e.target
        if(this.value.length < 4){
            pwMsg.innerText = "비밀번호는 4글자 이상입니다."
            pwMsg.style.color = "red";
        } else {
            pwMsg.innerText = "비밀번호는 사용가능합니다."
            pwMsg.style.color = "green";
        }
    };
</script>

</body>
</html>
