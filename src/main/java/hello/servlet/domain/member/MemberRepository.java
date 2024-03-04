package hello.servlet.domain.member;

import java.util.*;

public class MemberRepository {
	/**
	 * 동시성 문제가 고려되지 않음
	 * - 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야 한다.
	 * */
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;

	// 싱글톤으로 MemberRepository 인스턴스 생성
	private static final MemberRepository instance = new MemberRepository();

	public static MemberRepository getInstance() {
		return instance; // 항상 getInstance() 메서드로 싱글톤 객체 접근
	}

	// 싱글톤 패턴으로 객체를 관리할때는 생성자를 private 접근제한자를 두어 다른 곳에서 생성을 못하도록 막아야 한다.
	private MemberRepository() {
	}

	public Member save(Member member){
		member.setId(++sequence);
		// store에 member 객체 저장
		store.put(member.getId(), member);
		return member;
	}

	public Member findById(Long id){
		return store.get(id);
	}

	// store 자체를 외부에서 직접적으로 건들지 않게 하기 위함
	public List<Member> findAll(){
		return new ArrayList<>(store.values());
	}

	// 테스트 등에서 사용
	public void clearStore(){
		store.clear();
	}
}
