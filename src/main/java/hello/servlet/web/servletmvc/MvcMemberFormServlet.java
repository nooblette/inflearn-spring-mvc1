package hello.servlet.web.servletmvc;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp를 바로 반환한다.
		String viewPath = "/WEB-INF/views/new-form.jsp";

		// RequestDispatcher : 컨트룰러(Controller)에서 뷰(View)로 이동할때 사용
		// 뷰(View, jsp 파일)로 경로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response); // 서블릿에서 지정한 경로의 JSP(viewPath)로 이동 (제어권을 JSP로 넘긴다)
	}
}
