package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// @Service    //스프링에서 확인할 수 있도록..
@Transactional  // JPA 사용을 위해 필요하다.
public class MemberService {

    private final MemberRepository memberRepository;

    // Test Case에서 Repository를 같이 관리하기 위함 = Dependency Injection
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 이렇게 MemberRepository 변수 = new MemoryMemberRepository();로 선언한 이유?
     * 1) MemberRepository 인터페이스의 제약을 따르겠다는 의도
     * 2) 사용하는 코드가 MemberRepository 인터페이스 제약을 따르기 때문에 향후 변경시 사용코드를 변경하지 않아도 된다.
     * 3) MemoryMemberRepository을 다른 클래스로 변경이 필요하면 선언하는 코드만 변경하면 됩니다. 사용하는 코드를 고민하지 않아도 된다.
     * 4) 다른 개발자들이 이 코드를 나중에 더 성능이 좋거나 동시성 처리가 가능한 종류의 구체적인 MemberRepository으로 변경해야 할 때 MemoryMemberRepository 변수 = new MemoryMemberRepository()으로
     * 선언이 되어 있다면, 변경 시점에 상당히 고민해야 하지만, MemberRepository 변수 = new MemoryMemberRepository()으로 선언이 되어 있다면
     * 편안하게 선언부를 변경할 수 있다.
     */

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 X
        // 이때 값은 optional이다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); // 만약 값이 있으면 이름이 존재하는 것이다.
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
