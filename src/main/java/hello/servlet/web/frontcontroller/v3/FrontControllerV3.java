package hello.servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {
	// url 매핑 정보 저장
	private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

	public FrontControllerV3() {
		controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getRequestURI() : 브라우저의 요청 URI를 반환한다.
		// - 예를 들어, 요청 URI가 localhost:8080/front-controller/v3/mebmers 라면 /front-controller/v3/mebmers(controllerV3Map의 Key와 동일한 구조)를 반환
		String requestURI = request.getRequestURI();

		// 요청 URI 경로에 적절한 ControllerV3 인터페이스의 구현 객체를 반환한다.
		// - 인터페이스 기반으로 일관성있게 인스턴스를 꺼낼 수 있다
		ControllerV3 controller = controllerV3Map.get(requestURI);

		// 매핑되는 컨트룰러가 없다면 요청 경로가 잘못된 것
		if(controller == null) {
			// 응답 코드로 404(NOT FOUND)를 클라이언트에게 반환한다.
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 프론트 컨트룰러는 컨트룰러에게 클라이언트의 요청 파라미터를 paramMap에 담아서 넘겨준다.
		Map<String, String> paramMap = createParamMap(request);
		ModelView modelView = controller.process(paramMap);

		String viewName = modelView.getViewName();
		MyView myView = viewResolver(viewName);

		// ModelView의 모델(Model)을 MyView 클래스의 render() 메서드 호출 시 함께 넘겨준다.
		myView.render(modelView.getModel(), request, response);

	}

	private static MyView viewResolver(String viewName) { // 뷰(View)의 논리 이름을 물리적 위치로 변환
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}

	private static Map<String, String> createParamMap(HttpServletRequest request) {
		// 요청 파라미터를 모두 순회하며 paramMap에 담는 로직은 service() 메서드의 포함된 로직보다 디테일한 로직이다 -> createParamMap 메서드로 추출
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
}
