package hello.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
	// urlPatterns에 매핑된 경로(/servlet/members/new-form)로 요청이 들어오면 서블릿(Servlet)을 기반으로 회원 등록 폼 화면을 내려준다.

	// memberRepository 인스턴스는 싱글톤이므로 객체 생성(new)을 할 수 없다(생성자의 접근 제한자를 private으로 막아놓음)
	// private MemberRepository memberRepository = new MemberRepository();
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HTTP 요청에 대한 응답으로 회원 등록 form html을 내려주어야 한다.
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		// HTML 본문 작성을 위한 Writer 객체 얻기
		PrintWriter writer = response.getWriter();

		// 자바 코드로 HTML 본문 작성 - 서블릿으로 개발할 경우 이처럼 HTML 코드를 모두 java 코드로 직접 작성해야하는 불편함이 있다(휴먼에러가 발생할 수 있고 오타 찾기도 어려움)
		writer.write("<!DOCTYPE html>\n" +
			"<html>\n" +
			"<head>\n" +
			"    <meta charset=\"UTF-8\">\n" +
			"    <title>Title</title>\n" +
			"</head>\n" +
			"<body>\n" +
			"<form action=\"/servlet/members/save\" method=\"post\">\n" +
			"    username: <input type=\"text\" name=\"username\" />\n" +
			"    age:      <input type=\"text\" name=\"age\" />\n" +
			" <button type=\"submit\">전송</button>\n" + "</form>\n" +
			"</body>\n" +
			"</html>\n");
	}
}
