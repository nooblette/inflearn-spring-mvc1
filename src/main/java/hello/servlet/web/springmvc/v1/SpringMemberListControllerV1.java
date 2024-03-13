package hello.servlet.web.springmvc.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SpringMemberListControllerV1 {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping("/springmvc/v1/members")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = memberRepository.findAll();

		// 모델(model)에 데이터 전달
		ModelAndView mv = new ModelAndView("members");
		mv.addObject("members", members);

		// 뷰(view)의 논리적 이름을 반환
		return mv;
	}
}
