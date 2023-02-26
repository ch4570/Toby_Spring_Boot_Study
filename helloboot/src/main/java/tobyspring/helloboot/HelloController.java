package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /*
    *   DispatcherServlet은 기본적으로 문자열을 return 하면 해당 문자열과 동일한
    *   View 이름을 찾는다. 따라서 404 Error를 발생시킨다.
    *   문자열을 그대로 화면에 return 하고 보여주려면 @ResponseBody를 사용해야 한다.
    * */
    @GetMapping
    @ResponseBody
    public String hello(String name) {

        // Objects.requireNonNull 메서드는 들어온 Object가 null 이면 예외를 발생시킨다.
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
