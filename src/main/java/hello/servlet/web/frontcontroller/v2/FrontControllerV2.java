package hello.servlet.web.frontcontroller.v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {
	// url 매핑 정보 저장
	private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

	public FrontControllerV2() {
		controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
		controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
		controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getRequestURI() : 브라우저의 요청 URI를 반환한다.
		// - 예를 들어, 요청 URI가 localhost:8080/front-controller/v2/mebmers 라면 /front-controller/v2/mebmers(controllerV2Map의 Key와 동일한 구조)를 반환
		String requestURI = request.getRequestURI();

		// 요청 URI 경로에 적절한 ControllerV2 인터페이스의 구현 객체를 반환한다.
		// - 인터페이스 기반으로 일관성있게 인스턴스를 꺼낼 수 있다
		ControllerV2 controller = controllerV2Map.get(requestURI);

		// 매핑되는 컨트룰러가 없다면 요청 경로가 잘못된 것
		if(controller == null) {
			// 응답 코드로 404(NOT FOUND)를 클라이언트에게 반환한다.
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 컨트룰러에게 HTTP 요청(HttpServletRequest), 응답(HttpServletResponse) 정보를 전달하여 호출한다.
		// 컨트룰러는 비즈니스 로직을 처리한 후 MyView를 반환한다.
		MyView view = controller.process(request, response);

		// Front Controller가 MyView의 render() 메서드를 호출하여 JSP로 포워드(forward)한다.
		view.render(request, response);
	}
}
