package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 참고 : HTTP 요청은 start line, Header, 한 칸 엔터, message body 순서로 구성되어있다.

		// HTTP 요청의 message body 내용을 Byte 코드로 조회
		ServletInputStream inputStream = request.getInputStream();

		// 조회한 Byte 코드를 자바가 제공하는 StreamUtils 라이브러리를 통해 문자열(String)으로 변환
		String messageBody = StreamUtils.copyToString(inputStream,
			StandardCharsets.UTF_8);// Byte <-> String 전환 시에는 charset을 반드시 명시
		System.out.println("messageBody = " + messageBody);

		response.getWriter().write("ok");
	}
}
