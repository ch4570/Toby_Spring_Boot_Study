package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void helloControllerTest() {
        HelloController helloController = new HelloController(name -> name);

        String result = helloController.hello("Test");

        Assertions.assertThat(result).isEqualTo("Test");
    }

    @Test
    void failsHelloControllerTest() {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            String result = helloController.hello(null);
        }).isInstanceOf(IllegalStateException.class);

        Assertions.assertThatThrownBy(() -> {
            String result = helloController.hello("");
        }).isInstanceOf(IllegalStateException.class);


    }
}
