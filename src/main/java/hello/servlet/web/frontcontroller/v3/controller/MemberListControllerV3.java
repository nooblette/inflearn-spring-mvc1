package hello.servlet.web.frontcontroller.v3.controller;

import java.util.List;
import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3 {
	private MemberRepository memberRepository = MemberRepository.getInstance();
	@Override
	public ModelView process(Map<String, String> paramMap) {
		List<Member> members = memberRepository.findAll();

		// 뷰(view)의 물리적 위치가 아닌 논리적 이름만 전달
		ModelView modelView = new ModelView("members");

		// 모델(model)에 데이터 전달
		modelView.getModel().put("members", members);

		return modelView;
	}
}
