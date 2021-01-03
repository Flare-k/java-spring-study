package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// interface를 implements한다.
public class MemoryMemberRepository implements MemberRepository{

    // 메모리에 저장하는 방식으로 우선 채택.. (나중에 데이터베이스에 저장)
    private static Map<Long, Member> store = new HashMap<>();   // 이처럼 공유될땐 원래 Concurrent HashMap을 쓰는 것이 좋다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 id를 꺼내는 내용, NULL이 나올 수 있으므로 Optional로 감싼다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // java8 lambda 이용.
        // store를 loop 돌린다. member.getName()이 파라미터로 넘어온 name과 같은지 확인
        // findAny는 하나라도 찾는 것.
        // 끝까지 돌아도 없으면 Optional이 null을 감싸서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store.values() == member
        // member를 리스트로 반환해준다.
        return new ArrayList<>(store.values());
    }
}
