package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// interface를 implements한다.
public class MemoryMemberRepository implements MemberRepository{

    // 메모리에 저장하는 방식으로 우선 채택.. (나중에 데이터베이스에 저장)
    private static Map<Long, Member> store = new HashMap<>();   // 이처럼 공유될땐 원래 Concurrent HashMap을 쓰는 것이 좋다.
    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
