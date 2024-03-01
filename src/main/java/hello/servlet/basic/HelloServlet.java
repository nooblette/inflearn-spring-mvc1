package hello.servlet.basic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // /hello로 시작하는 URI 경로로 요청이 오면 HelloServlet 클래스가 동작한다.
public class HelloServlet extends HttpServlet {
	@Override
	// HelloServlet 클래스가 동작하면 service 메서드가 호출된다.
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("HelloServlet.service");
		System.out.println("request = " + request);
		System.out.println("response = " + response);

		/**
		 * 요청 정보 읽기
		 * */
		// getParameter() : ServletRequest 인터페이스에 제공하는 메서드, 이를 통해 쿼리 파라미터(쿼리 스트링)로 전달된 값을 편리하게 조회
		String username = request.getParameter("username");
		System.out.println("username = " + username);

		/**
		 * 응답 내려주기
		 * */
		// HTTP 응답 header에 넣어줄 정보
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		// HTTP body에 넣을 메시지
		response.getWriter().write("hello " + username);
	}
}
