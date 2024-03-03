package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 	응답을 HTML 형식으로 내려준다.
 		 */

		// Content-Type: text/html;charset=utf-8
		response.setContentType("text/html"); // content-type 헤더 값이 html로 명시되어 있어야 웹 브라우저가 랜더링을 수행한다.
		response.setCharacterEncoding("utf-8");

		// HTML 본문 작성
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("	<body>");
		writer.println("		<div>안녕?</div>");
		writer.println("	</body>");
		writer.println("</html>");
	}
}
