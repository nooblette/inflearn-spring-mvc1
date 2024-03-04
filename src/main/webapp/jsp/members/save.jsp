<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% // java 코드(비즈니스 로직)를 넣는 부분
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");

	// JSP도 결국 Serevlet으로 변환되어 사용된다. (MemberSaveServlet java 로직이 그대로 호출된다고 보면 된다.)
    // 따라서 HttpServletRequest와 HttpServletResponse는 별도의 import 없이 바로 사용할 수 있다.
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    memberRepository.save(member);
%>

<!-- html 코드 - Writer 객체의 write() 메서드에 넘겨주는 값을 그대로 작성한다고 보면 된다.-->
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a> <!-- 목록으로 이동 -->
</body>
</html>
