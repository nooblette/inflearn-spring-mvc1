package hello.servlet.web.frontcontroller.v2.controller;

import java.io.IOException;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberFormControllerV2 implements ControllerV2 {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 뷰(View, jsp 파일)로 경로 이동
		return new MyView("/WEB-INF/views/new-form.jsp");
	}
}
