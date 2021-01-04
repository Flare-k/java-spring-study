package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 직접 스프링 빈 작성
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /**
     * memberService(), memberRepository() 둘다 스프링 빈에 등록하고,
     * 스프링 빈에 등록되어 있는 memberRepository()를 MemberService에 넣어준다.
     * Controller는 컴포넌트 스캔방식을 사용한다.
     */
}

/**
 * 과거엔 자바 코드로 스프링 빈을 등록하지 않고 xml로 설정했다.
 * 지금은 실무에서 거의 xml로 사용하지 않는다.
 * DI에는 필드 주입, setter 주입, 생성자 주입 세 가지 방법이 있다. -> 생성자 주입을 권장
 * 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
 * 정형화되지 않거나 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
 */