package hello.servlet.web.frontcontroller.v4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {
	// url 매핑 정보 저장
	private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

	public FrontControllerV4() {
		controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getRequestURI() : 브라우저의 요청 URI를 반환한다.
		// - 예를 들어, 요청 URI가 localhost:8080/front-controller/v4/mebmers 라면 /front-controller/v4/mebmers(controllerV4Map의 Key와 동일한 구조)를 반환
		String requestURI = request.getRequestURI();

		// 요청 URI 경로에 적절한 ControllerV4 인터페이스의 구현 객체를 반환한다.
		// - 인터페이스 기반으로 일관성있게 인스턴스를 꺼낼 수 있다
		ControllerV4 controller = controllerV4Map.get(requestURI);

		// 매핑되는 컨트룰러가 없다면 요청 경로가 잘못된 것
		if(controller == null) {
			// 응답 코드로 404(NOT FOUND)를 클라이언트에게 반환한다.
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 프론트 컨트룰러는 컨트룰러에게 클라이언트의 요청 파라미터를 paramMap 객체에 담고 응답 데이터를 전달받을 model 객체를 넘겨준다.
		Map<String, String> paramMap = createParamMap(request);
		Map<String, Object> model = new HashMap<>();

		// 컨트룰러의 비즈니스 로직을 수행하는 process() 메서드는 뷰의 논리적 이름(viewName)을 바로 반환한다.
		String viewName = controller.process(paramMap, model);

		MyView myView = viewResolver(viewName);

		// 모델(Model)에 해당하는 model 객체를 MyView 클래스의 render() 메서드 호출 시 함께 넘겨준다.
		myView.render(model, request, response);

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
