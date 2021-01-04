package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /**
     * Test 코드는 한글로 해도 큰 문제 없다.
     */

    MemberService memberService = new MemberService();

    /**
     * given - when - then
     * = 상황이 주어지고, 실행했을 때 결과가 나와야 한다.
     */
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        // 회원가입한게 리포지토리에 있는게 맞는지 확인하기 위한 검증
        // option+Cmd+V 하면 Optional 포맷이 만들어짐
        Member findMember = memberService.findOne(saveId).get();
        // option + Enter -> static으로 만들 수 있다.
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}