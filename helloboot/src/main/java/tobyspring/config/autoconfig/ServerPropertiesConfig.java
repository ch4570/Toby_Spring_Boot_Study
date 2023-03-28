package tobyspring.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@MyAutoConfiguration
public class ServerPropertiesConfig {

    @Bean
    public ServerProperties serverProperties(Environment environment) {

        // Binder 클래스를 이용하면 Property를 쉽게 바인딩 할 수 있다.
        return Binder.get(environment).bind("", ServerProperties.class).get();
    }
}
