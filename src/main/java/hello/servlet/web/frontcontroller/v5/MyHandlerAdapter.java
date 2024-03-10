package hello.servlet.web.frontcontroller.v5;

import java.io.IOException;

import hello.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {

	// 어댑터가 매개변수로 전달받은 handler(컨트룰러)를 처리할 수 있는지 판단하는 메서드
	boolean supports(Object handler);

	// 실제 컨트룰러를 호출하고 컨트룰러가 처리한 결과를 ModelView 객체로 반환하는 메서드
	ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
