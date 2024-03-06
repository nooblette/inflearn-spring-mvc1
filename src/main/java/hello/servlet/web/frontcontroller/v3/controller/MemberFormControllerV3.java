package hello.servlet.web.frontcontroller.v3.controller;

import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberFormControllerV3 implements ControllerV3 {
	@Override
	public ModelView process(Map<String, String> paramMap) {
		// 컨트룰러는 뷰(jsp)의 물리적인 위치가 아닌 논리적인 이름만 전달한다.
		//  - 논리적인 이름 -> 물리적인 위취로 변환은 ViewResolver() 메서드에서 담당한다
		return new ModelView("new-form");
	}
}
