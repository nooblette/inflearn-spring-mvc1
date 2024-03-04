<!-- JSP 파일임을 나타내는 용도, 반드시 선언해주어야 한다. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- submit 버튼을 누르면 action 에 명시한 /jsp/members/save.jsp 경로로 POST 요청이 전송된다. -->
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
