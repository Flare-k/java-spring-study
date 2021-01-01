package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // /hello라고 들어오면 이 메서드를 호출해준다.
    public String hello(Model model) {  // MVC의 Model이다.
        // 키-밸류
        model.addAttribute("data", "hello!");
        return "hello"; // /resources/templates/에 있는 html 파일명으로 리턴하는 것.
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(View-Resolver)가 화면을 찾아서 처리한다.
        // 스프링 부트 템플릿 엔진 기본 viewName 매핑
        // `resources:templates/` + {ViewName} + `.html`
        // 해당 html 파일로 모델의 키값을 던져준다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    @GetMapping("hello-string")
    @ResponseBody   // http에 있는 바디 파트를 의미. 바디 파트에 해당 데이터를 직접 넣겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // key-value 구조로 나온다. 
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
