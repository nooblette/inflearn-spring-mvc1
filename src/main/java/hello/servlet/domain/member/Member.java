package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private Long id; // 식별자 - DB 테이블의 pk와 매핑(우리는 메모리에서만 사용)
	private String username;
	private int age;

	public Member() {
	}

	public Member(String username, int age) {
		this.username = username;
		this.age = age;
	}
}
