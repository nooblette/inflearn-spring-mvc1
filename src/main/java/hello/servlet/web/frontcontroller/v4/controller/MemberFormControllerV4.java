package hello.servlet.web.frontcontroller.v4.controller;

import java.util.Map;

import hello.servlet.web.frontcontroller.v4.ControllerV4;

public class MemberFormControllerV4 implements ControllerV4 {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		// Controller V4는 ModelView 객체를 생성하고 반환할 필요 없이 뷰(View)의 논리적 이름만 반환하면 된다.
		return "new-form";
	}
}
