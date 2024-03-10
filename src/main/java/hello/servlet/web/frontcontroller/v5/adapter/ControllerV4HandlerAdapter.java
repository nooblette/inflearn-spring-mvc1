package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ControllerV4를 지원하는 어댑터
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
	@Override
	public boolean supports(Object handler) {
		// instanceof : 매개변수로 전달받은 handler 객체가 ControllerV3 인터페이스를 구현한 객체이면 참(true)을 반환 / 아니면 거짓(false)을 반환
		return (handler) instanceof ControllerV4;
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		/**
		 * 핸들러(컨트룰러) 호출
		 * - HTTP 요청에 대해 비즈니스 로직을 처리하기 위한 process() 메서드 호출
		 * */
		ControllerV4 controller = (ControllerV4)handler;

		Map<String, String> paramMap = createParamMap(request);
		HashMap<String, Object> model = new HashMap<>();

		String viewName = controller.process(paramMap, model);

		// 어댑터가 문자열 형식의 viewName(뷰의 논리적 이름)을 ModelView 타입의 객체로 변환(220v를 110v로 바꾸는 돼지코처럼..)하여 반환한다.
		ModelView modelView = new ModelView(viewName);
		modelView.setModel(model);

		return modelView;
	}

	private static Map<String, String> createParamMap(HttpServletRequest request) {
		// 요청 파라미터를 모두 순회하며 paramMap에 담는 로직
		// - handle() 메서드의 포함된 로직보다 디테일한 로직이므로 createParamMap 메서드로 추출하여 구현한다.
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
}
