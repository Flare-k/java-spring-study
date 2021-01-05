package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 직접 스프링 빈 작성
@Configuration
public class SpringConfig {
/*

    private DataSource dataSource;

    @Autowired  //DataSource를 spring에서 제공해준다
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/
    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    /**
     * [Spring Data JPA]
     * Spring Container에서 MemberRepository를 찾는다.
     * SpringDataJpaMemberRepository과 같은 인터페이스만 만들어놓으면 Spring Data JPA가 구현체를 만들어낸다.
     * 그리고 Spring Bean에 등록한다.
     */

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
/*

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateRepository(dataSource);
        return new JpaMemberRepository(em);
    }
*/

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