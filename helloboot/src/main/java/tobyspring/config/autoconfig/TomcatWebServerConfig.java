//package tobyspring.config.autoconfig;
//
//
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//import org.springframework.util.ClassUtils;
//import tobyspring.config.MyAutoConfiguration;
//
//@MyAutoConfiguration
//
//// @Conditional 애너테이션을 사용해서 Bean을 등록할지 여부를 결정할 수 있다.
//// @Conditional은 Configuration 클래스와 Bean 메서드에 사용할 수 있다.
//// @Conditional 애너테이션에는 Condition 인터페이스를 구현한 클래스를 넣어주어야 한다.
//@Conditional(TomcatWebServerConfig.TomcatCondition.class)
//public class TomcatWebServerConfig {
//
//
//    @Bean(name = "tomcatWebServerFactory")
//    public ServletWebServerFactory servletWebServerFactory() {
//        return new TomcatServletWebServerFactory();
//    }
//
//    static class TomcatCondition implements Condition {
//
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat",
//                    context.getClassLoader());
//        }
//    }
//}
