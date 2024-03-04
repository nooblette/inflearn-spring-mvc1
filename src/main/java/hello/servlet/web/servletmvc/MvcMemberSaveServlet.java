package hello.servlet.web.servletmvc;

import java.io.IOException;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// service() 메서드가 동작하는지 확인
		System.out.println("MemberSaveServlet.service");

		/**
		 * 클라이언트 요청 읽기
		 * */
		// 웹 브라우저의 HTTP 요청 값을 읽어온다.
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		/**
		 * 비즈니스 로직
		 * */
		Member member = new Member(username, age);
		memberRepository.save(member); // Member 저장

		/**
		 * 모델에 데이터 전달
		 * */
		// MVC 중 모델(Model)에 데이터를 보관한다.
		request.setAttribute("member", member); // key-value 쌍, request 객체에 정의된 map 내부 저장소가 Model 역할을 수행한다.

		// 뷰(View, jsp 파일)로 경로 이동
		String viewPath = "/WEB-INF/views/save-result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response); // request 객체와 response 객체를 넘기면서(Model) JSP(View)로 경로 이동(forward)
	}
}
