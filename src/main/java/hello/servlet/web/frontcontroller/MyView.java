package hello.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyView {
	private String viewPath;

	public MyView(String viewPath) {
		this.viewPath = viewPath;
	}

	// JSP로 이동(렌더링)하도록 동작하기 위함
	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

		// JSP로 렌더링
		dispatcher.forward(request, response);
	}

	public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// model의 데이터를 jsp에서 사용할 수 있도록 순회하며 HttpServletRequest 서블릿의 setAttribute() 메서드를 호출한다.
		modelToRequestAttribute(model, request);

		// JSP로 렌더링
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}

	private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		model.forEach((key, value) -> request.setAttribute(key, value));
	}
}
