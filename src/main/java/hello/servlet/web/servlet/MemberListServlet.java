package hello.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 비즈니스 로직
		 * */
		// 전체 member 목록 조회
		List<Member> members = memberRepository.findAll();

		/**
		 * 응답 HTML 내려주기
		 * */
		// 응답 헤더 세팅
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		// 응답 HTML 코드 직접 작성
		PrintWriter writer = response.getWriter();
		writer.write("<html>");
		writer.write("<head>");
		writer.write(" <meta charset=\"UTF-8\">");
		writer.write(" <title>Title</title>");
		writer.write("</head>");
		writer.write("<body>");
		writer.write("<a href=\"/index.html\">메인</a>");
		writer.write("<table>");
		writer.write(" <thead>");
		writer.write(" <th>id</th>");
		writer.write(" <th>username</th>");
		writer.write(" <th>age</th>");
		writer.write(" </thead>");
		writer.write(" <tbody>");

		// Members 데이터를 동적으로 html에 출력한다.
		for (Member member: members) {
			writer.write(" <tr>");
			writer.write(" <td>" + member.getId() + "</td>");
			writer.write(" <td>" + member.getUsername() + "</td>");
			writer.write(" <td>" + member.getAge() + "</td>");
			writer.write(" </tr>");
		}

		writer.write(" </tbody>");
		writer.write("</table>");
		writer.write("</body>");
		writer.write("</html>");
	}
}
