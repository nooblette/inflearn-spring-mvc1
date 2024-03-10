package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Controller V3를 지원하는 어댑터
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
	@Override
	public boolean supports(Object handler) {
		// instanceof : 매개변수로 전달받은 handler 객체가 ControllerV3 인터페이스를 구현한 객체이면 참(true)을 반환 / 아니면 거짓(false)을 반환
		return (handler) instanceof ControllerV3;
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		/**
		 * 핸들러(컨트룰러) 호출
		 * - HTTP 요청에 대해 비즈니스 로직을 처리하기 위한 process() 메서드 호출
		 * */
		// handle() 메서드는 다양한 타입의 핸들러를 유연하게 처리하기 위해 메서드 시그니처에서 handler 매개변수의 타입을 Object 객체로 정의했다.
		// handle()을 구현할때는 handler 매개변수를 사용하기 위해 강제 형변환(타입캐스팅, 부모 객체 -> 자식 객체로 타입 변환)을 해야 한다.(Object 타입으로는 할 수 있는게 없다.)

		// handle() 메서드를 호출하기 전에 supports() 메서드를 호출하여 handler가 ControllerV3 인터페이스를 구현했는지 검증하였으므로 ControllerV3로 바로 타입캐스팅 할 수 있다.
		ControllerV3 controller = (ControllerV3)handler;

		Map<String, String> paramMap = createParamMap(request);
		return controller.process(paramMap);
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
