package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 1. 클라이언트에서 서버로 요청시 파라미터 전송 기능
 *   - http://localhost:8080/request-param?username=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RequestParamServlet.service"); // requestParamServlet 서블릿이 동작하는지 확인

		System.out.println("[전체 파라미터 조회] - start");
		Enumeration<String> parameterNames = request.getParameterNames(); // getParameterNames() : 모든 요청 파라미터의 key 값을 조회
		parameterNames.asIterator()
				.forEachRemaining(parameterName -> System.out.println(parameterName + ": " + request.getParameter(parameterName)));
		System.out.println("[전체 파라미터 조회] - end");
		System.out.println();

		System.out.println("[단일 파라미터 조회] - start");
		// getParameter() : 매개변수로 전달한 key 값을 기준으로 특정 파라미터의 value 를 조회
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		System.out.println("username = " + username);
		System.out.println("age = " + age);
		System.out.println("[단일 파라미터 조회] - end");
		System.out.println();

		System.out.println("[이름이 같은 복수 파라미터 조회 - start");
		String[] usernames = request.getParameterValues("username");
		for (String name : usernames) {
			System.out.println("name = " + name);

		}
		System.out.println("[이름이 같은 복수 파라미터 조회 - end");

		// 응답 내려주기
		response.getWriter().write("ok");
	}
}
