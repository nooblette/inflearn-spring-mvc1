package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {
	// memberRepository 인스턴스는 싱글톤 객체이므로 직접 생성(new 키워드)하는 것이 아닌 getInstance() 메서드로 조회해야한다.
	// 스프링을 사용할 경우, 스프링 자체가 싱글톤을 보장하므로 개발자가 직접 싱글톤 코드를 작성할 필요가 없다.
	MemberRepository memberRepository = MemberRepository.getInstance();

	@AfterEach
	void afterEach(){
		// 테스트 코드 동작 후 실행
		memberRepository.clearStore();
	}

	@Test
	void save() {
		// given
		Member hello = new Member("hello", 20);

		// when
		Member savedMember = memberRepository.save(hello);

		// then
		Member findMember = memberRepository.findById(savedMember.getId());// 저장한 member 조회
		assertThat(findMember).isEqualTo(savedMember);
	}

	@Test
	void findAll() {
		// given
		Member member1 = new Member("member1", 20);
		Member member2 = new Member("member2", 30);

		memberRepository.save(member1);
		memberRepository.save(member2);

		// when
		List<Member> result = memberRepository.findAll();

		// then
		assertThat(result.size()).isEqualTo(2);
		assertThat(result).contains(member1, member2); // result 리스트 안에 member1 객체와 member2 객체가 포함되어 있는가
	}
}