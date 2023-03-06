package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Test
@interface UnitTest {
}

public class HelloServiceTest {

    @FastUnitTest
    void SimpleHelloService() {

        SimpleHelloService helloService = new SimpleHelloService();

        String result = helloService.sayHello("Test");

        Assertions.assertThat(result).isEqualTo("Hello Test");
    }

    @UnitTest
    void helloDecoratorTest() {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String result = decorator.sayHello("Test");

        Assertions.assertThat(result).isEqualTo("*Test*");
    }
}
