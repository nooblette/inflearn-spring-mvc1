package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// Jackson library는 java bean(java property) 접근법에 따라 기본적으로 Getter, Setter를 호출한다.
@Getter
@Setter
public class HelloData {
	private String username;
	private int age;
}
