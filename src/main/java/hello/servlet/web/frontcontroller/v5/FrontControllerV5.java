package hello.servlet.web.frontcontroller.v5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {
	// 기존 구현 방식 : controllerMap이 특정 버전의 컨트룰러만 담을 수 있다.
	// private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

	// V5 구현 방식 : 어떤 핸들러(= 컨트룰러의 넓은 의미)든 유연하게 담기 위해 Map의 Value를 Object 타입으로 정의한다.
	// Object : 모든 java 객체의 최상위 부모 객체
	private final Map<String, Object> handlerMappingMap = new HashMap<>();

	// 여러개의 어댑터 중 특정 핸들러를 지원하는 어댑터를 찾기 위해 handlerAdapters 리스트에 어댑터를 담아둔다.
	private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

	public FrontControllerV5() {
		initHandlerMappingMap();
		initHandlerAdapters();
	}

	private void initHandlerMappingMap() {
		handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
	}
	private void initHandlerAdapters() {
		handlerAdapters.add(new ControllerV3HandlerAdapter());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 요청 URI에 대해 핸들러(컨트룰러) 매핑 정보 조회
		 * */
		// getObject() : 요청 URI 경로(request)에 매핑되는 적절한 핸들러(handler)를 꺼낸다.
		Object handler = getObject(request);
		if(handler == null) {
			// 매핑되는 핸들러가 없다면 요청 경로가 잘못된 것, 응답 코드로 404(NOT FOUND)를 클라이언트에게 반환한다.
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		/**
		 * 핸들러 어댑터 목록 조회 및 처리(handle)
		 * - 핸들러는 비즈니스 로직 처리 후 ModelView 객체를 반환한다.
		 * */
		// getHandlerAdapter() : handler 핸들러를 처리할 수 있는 핸들러 어댑터를 handlerAdapters 목록에서 찾아서 반환한다.
		MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
		// handle() 메서드를 호출하여 요청 파라미터에 대해 핸들러에게 처리(handle)를 요청하고 응답으로 ModelView를 얻는다.
		ModelView modelView = handlerAdapter.handle(request, response, handler);

		/**
		 * HTML 응답을 위한 렌더링
		 * */
		String viewName = modelView.getViewName(); 	// 뷰(View, jsp)의 논리적 이름 반환
		MyView myView = viewResolver(viewName);		// 뷰(View)의 논리 이름을 물리적 위치로 변환

		// ModelView의 모델(Model)을 MyView 클래스의 render() 메서드 호출 시 함께 넘겨준다.
		myView.render(modelView.getModel(), request, response);
	}

	private MyHandlerAdapter getHandlerAdapter(Object handler) {
		for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
			if(handlerAdapter.supports(handler)){
				return handlerAdapter;
			}
		}
		throw new IllegalArgumentException(handler + " 핸들러를 처리할 적절한 handlerAdapter를 찾을 수 없습니다.");
	}

	private Object getObject(HttpServletRequest request) {
		// getRequestURI() : 브라우저의 요청 URI를 반환한다.
		// - 예를 들어, 요청 URI가 localhost:8080/front-controller/v5/mebmers 라면 /front-controller/v5/mebmers를 반환
		String requestURI = request.getRequestURI();

		return handlerMappingMap.get(requestURI);
	}

	private static MyView viewResolver(String viewName) { // 뷰(View)의 논리 이름을 물리적 위치로 변환
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
}