package hello.servlet.web.servletmvc;

import java.io.IOException;
import java.util.List;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {
	private MemberRepository memberRepository = MemberRepository.getInstance();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 비즈니스 로직
		 * */
		// 전체 member 목록 조회
		List<Member> members = memberRepository.findAll();

		/**
		 * 모델에 데이터 전달
		 * */
		request.setAttribute("members", members); // key-value 쌍

		// 뷰(View, jsp 파일)로 경로 이동 - 경로 이동하는 코드가 계속 반복되고 있다
		String viewPath = "/WEB-INF/views/members.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
}
