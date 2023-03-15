package tobyspring.config.autoconfig;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration

// @Conditional 애너테이션을 사용해서 Bean을 등록할지 여부를 결정할 수 있다.
// @Conditional 애너테이션에는 Condition 인터페이스를 구현한 클래스를 넣어주어야 한다.
@Conditional(JettyWebServerConfigRefactor.JettyCondition.class)
public class JettyWebServerConfigRefactor {

    // @Bean의 Element로 이름을 지정하지 않으면 메서드 이름이 Bean의 이름이된다.
    @Bean(name = "jettyWebServerFactory")
    // 지금 등록하려는 Bean을 개발자가 등록한적이 있으면 등록되지 않도록한다.
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }


    static class JettyCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("org.eclipse.jetty.server.Server",
                    context.getClassLoader());
        }
    }
}
