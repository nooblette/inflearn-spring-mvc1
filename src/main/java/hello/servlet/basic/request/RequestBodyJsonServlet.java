package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

	// JSON 형식의 문자열을 객체로 매핑하기 위한 json 라이브러리를 호출한다.
	// 스프링부트는 기본적으로 Jackson 라이브러러리를 제공한다.
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		System.out.println("messageBody = " + messageBody);

		// readValue() : 문자열(String) 형식의 messageBody 값을 HelloData 객체 형식으로 매핑
		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

		System.out.println("helloData.getUsername() = " + helloData.getUsername());
		System.out.println("helloData.getAge() = " + helloData.getAge());

		response.getWriter().write("ok");
	}
}
