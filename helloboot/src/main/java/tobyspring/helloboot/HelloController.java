package tobyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
@RestController
public class HelloController implements ApplicationContextAware {

    private final HelloService helloService;

    // ApplicationContext는 final로 선언이 불가능하다.
    // 이미 객체가 생성되고 Life-Cycle 메서드가 실행되기 때문이다.
    private ApplicationContext applicationContext;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /*
    *   DispatcherServlet은 기본적으로 문자열을 return 하면 해당 문자열과 동일한
    *   View 이름을 찾는다. 따라서 404 Error를 발생시킨다.
    *   문자열을 그대로 화면에 return 하고 보여주려면 @ResponseBody를 사용해야 한다.
    * */
    @GetMapping("/hello")
    public String hello(String name) {

        if(name == null || name.trim().length() == 0) throw new IllegalStateException();

        // Objects.requireNonNull 메서드는 들어온 Object가 null 이면 예외를 발생시킨다.
//        return helloService.sayHello(Objects.requireNonNull(name));

        return helloService.sayHello(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
