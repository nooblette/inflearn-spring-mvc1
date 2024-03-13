package hello.servlet.web.springmvc.v2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
	private MemberRepository memberRepository = MemberRepository.getInstance();
	@RequestMapping("/new-form")
	public ModelAndView newForm() {
		return new ModelAndView("new-form");
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		// 모델(model)에 데이터 전달
		ModelAndView mv = new ModelAndView("save-result"); // 뷰(View)의 논리적 이름
		mv.addObject("member", member);

		// 모델뷰 반환
		return mv;
	}

	@RequestMapping
	public ModelAndView members() {
		List<Member> members = memberRepository.findAll();

		// 모델(model)에 데이터 전달
		ModelAndView mv = new ModelAndView("members"); // 뷰(View)의 논리적 이름
		mv.addObject("members", members);

		// 모델뷰 반환
		return mv;
	}
}
