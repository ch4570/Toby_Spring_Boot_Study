//package tobyspring.config.autoconfig;
//
//
//import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import tobyspring.config.ConditionalMyOnClass;
//import tobyspring.config.MyAutoConfiguration;
//
//@MyAutoConfiguration
//@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
//public class JettyWebServerConfig {
//
//    // @Bean의 Element로 이름을 지정하지 않으면 메서드 이름이 Bean의 이름이된다.
//    @Bean(name = "jettyWebServerFactory")
//    public ServletWebServerFactory servletWebServerFactory() {
//        return new JettyServletWebServerFactory();
//    }
//
//}
