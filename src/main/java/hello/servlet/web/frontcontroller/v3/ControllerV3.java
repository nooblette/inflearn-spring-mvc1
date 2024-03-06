package hello.servlet.web.frontcontroller.v3;

import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;

public interface ControllerV3 {
	// ControllerV2의 process() : MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	//  - HttpServletRequest, HttpServletResponse에 종속적이다.
	ModelView process(Map<String, String> paramMap); // 특정 기술(Servlet)에 종속적이지 않고 코드도 간결해졌다.
}
