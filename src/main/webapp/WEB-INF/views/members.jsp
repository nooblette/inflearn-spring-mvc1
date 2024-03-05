<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jstl 라이브러리르 사용하기 위함-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <!-- members 리스트를 순회하며 화면 출력을 깔끔하게 하기 위해 jstl 활용-->
    <c:forEach var="item" items="${members}"> <!-- members(= 서블릿에서 전달한 모델(model)의 key 값) 리스트에서 원소(item)을 하나씩 꺼내와서 var="item"에 할당한다.-->
        <tr>
            <td>${item.id}</td>
            <td>${item.username}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>