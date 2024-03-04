package hello.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// service() 메서드가 동작하는지 확인
		System.out.println("MemberSaveServlet.service");

		/**
		 * 클라이언트 요청 읽기
		 * */
		// 웹 브라우저의 HTTP 요청 값을 읽어온다.
		// 클라이언트의 HTTP 요청이 GET 요청의 쿼리 파라미터(쿼리 스트링, ?username=kim) 혹은 POST 요청의 HTML 입력 Form 방식일 경우 요청 파라미터 값을 getParameter() 메서드로 조회한다.
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age")); // getParameter() 메서드의 반환 값은 String 이므로 타입 변환이 필요하다.

		/**
		 * 비즈니스 로직
		 * */
		// 웹 브라우저의 요청 값으로 Member 객체 생성
		Member member = new Member(username, age);

		// Member 저장
		memberRepository.save(member);

		/**
		 * 응답 HTML 내려주기
		 * */
		// 응답 헤더 세팅
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		// 응답 HTML 코드 직접 작성
		PrintWriter writer = response.getWriter();
		writer.write("<html>\n" +
			"<head>\n" +
			" <meta charset=\"UTF-8\">\n" + "</head>\n" +
			"<body>\n" +
			"성공\n" +
			"<ul>\n" +
			"    <li>id=" + member.getId() + "</li>\n" + // 동적으로 코드를 작성할 수 있다.
			"    <li>username=" + member.getUsername() + "</li>\n" +
			" <li>age=" + member.getAge() + "</li>\n" + "</ul>\n" +
			"<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
			"</html>");
	}
}
