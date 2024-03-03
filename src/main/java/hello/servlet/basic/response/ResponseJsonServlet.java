package hello.servlet.basic.response;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

	// Json 라이브러리(스프링 부트는 기본적으로 Jackson 탑재)를 사용하기 위해 ObjectMapper 객체를 생성하고 writeValueAsString() 메서드를 호출한다.
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Content-Type: application/json
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		// 응답 JSON 형식에 매핑되는 HelloData 객체 생성 및 값 세팅
		HelloData helloData = new HelloData();
		helloData.setUsername("오민혁");
		helloData.setAge(28);

		// JSON도 결국 문자열(String)이므로 HelloData 객체를 문자열로 변환 (HelloData -> {"username":"오민혁", "age":20})
		String result = objectMapper.writeValueAsString(helloData);
		response.getWriter().write(result);
	}
}
