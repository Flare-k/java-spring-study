package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면 저장된 회원 반환
    // Optional은 만약 데이터를 받아올때, NULL이 올 수도 있다. 이때 Optional로 감싸서 반환한다.
    Optional<Member> findById(Long id); // id로 회원찾기
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
