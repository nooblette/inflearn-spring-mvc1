package hello.servlet.web.frontcontroller.v1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {
	// url 매핑 정보 저장
	private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

	public FrontControllerV1() {
		controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
		controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
		controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// FrontControllerV1 클래스의 service() 메서드가 호출되는지 확인
		System.out.println("FrontControllerV1.service");

		// getRequestURI() : 브라우저의 요청 URI를 반환한다.
		// - 예를 들어, 요청 URI가 localhost:8080/front-controller/v1/mebmers 라면 /front-controller/v1/mebmers(controllerV1Map의 Key와 동일한 구조)를 반환
		String requestURI = request.getRequestURI();

		// 요청 URI 경로에 적절한 ControllerV1 인터페이스의 구현 객체를 반환한다.
		// - 인터페이스 기반으로 일관성있게 인스턴스를 꺼낼 수 있다
		ControllerV1 controller = controllerV1Map.get(requestURI);

		// 매핑되는 컨트룰러가 없다면 요청 경로가 잘못된 것
		if(controller == null) {
			// 응답 코드로 404(BAD REQUEST)를 클라이언트에게 반환한다.
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 컨트룰러에게 HTTP 요청(HttpServletRequest), 응답(HttpServletResponse) 정보를 전달하여 호출한다.
		controller.process(request, response);
	}
}
