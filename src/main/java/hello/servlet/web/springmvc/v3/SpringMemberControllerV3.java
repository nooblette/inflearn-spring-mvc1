package hello.servlet.web.springmvc.v3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@GetMapping(value = "/new-form")
	public String newForm() {
		// 스프링 MVC 프레임워크 기반에서는 컨트룰러가 ModelAndView 객체를 반환해도 되지만 문자열(String)을 반환해도 뷰 이름으로 인식하고 렌더링 프로세스가 진행된다.
		return "new-form";
	}

	@PostMapping(value = "/save")
	public String save(@RequestParam("username") String username, // @RequestParam : HTTP 요청에 포함된 파라미터를 바로 받아올 수 있다(타입캐스팅 또한 지원한다)
					   @RequestParam("age") int age,
					   Model model) { // 데이터를 전달할 Model 객체도 매개변수로 받을 수 있다.

		Member member = new Member(username, age);
		memberRepository.save(member);

		// 모델(model)에 데이터 전달
		model.addAttribute("member", member);

		// 뷰(view) 이름을 문자열로 반환
		return "save-result";
	}

	@GetMapping
	public String members(Model model) {  // 데이터를 전달할 Model 객체도 매개변수로 받을 수 있다.
		List<Member> members = memberRepository.findAll();

		// 모델(model)에 데이터 전달
		model.addAttribute("members", members);

		// 뷰(view) 이름을 문자열로 반환
		return "members";
	}
}
