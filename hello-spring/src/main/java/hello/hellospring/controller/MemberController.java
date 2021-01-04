package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /**
     * createMemberForm에서 input 태그의 name 부분이 서버로 넘어올 때 Key가 된다.
     * 등록 버튼을 누르면 action url인 /members/new로 post 방식으로 넘어온다.
     * 그럼 MemberController에 PostMapping 부분에 들어온다.
     * 스프링이 input 태그의 name의 "name"을 보고 MemberForm의 name에 넣어준다.
     */

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // members를 model에 담아서 화면에 보낸다.
        return "members/memberList";
    }
}
/* memberService를 스프링이 컨테이너에 있는 memberService 객체와 연결해준다.
 * MemberService는 순수한 Java 코드이기 때문에 스프링에서 알지 못한다. @Service라는 어노테이션을 추가해줘야 한다.
 * Controller와 Service를 연결시켜줘야 한다.Autowired를 쓰면 MemberController가 생성될 때 스프링 빈에 등록되어 있는
 * 객체를 가져다가 넣어준다. 이게 바로 Dependency Injection이다.
 * Service는 Repository를 필요로 한다.
 *
 * Controller를 통해 외부 요청을 받고 Service에서 비즈니스 로직을 만들고 Repository에서 데이터를 저장하는 것이 정형화된 패턴이다.
 *
 * <스프링 빈을 등록하는 2가지 방법>
 *   - 컴포넌트 스캔과 자동 의존관계 설정 (내가 한 방법)
 *   - 자바 코드로 직접 스프링 빈 등록하기
 *
 * 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본적으로 싱글톤으로 등록한다.
 */


/*
 * 스프링 컨테이너에 @Controller이 있으면 MemberController 객체를 생성해서 스프링이 넣어둔다.
 * 그리고 스프링이 관리한다.
 * 이것을 스프링 컨테이너에서 스프링 빈이 관리된다고 표현한다.
 * 스프링이 관리하게 되면 다 스프링 컨테이너에 등록되고 스프링 컨테이너에서 받아쓰는 형태로 바꿔야 한다.
 */
