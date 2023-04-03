//package tobyspring.config.autoconfig;
//
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import tobyspring.config.ConditionalMyOnClass;
//import tobyspring.config.EnableMyAutoConfigurationProperties;
//import tobyspring.config.MyAutoConfiguration;
//
//@MyAutoConfiguration
//@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
//@EnableMyAutoConfigurationProperties(ServerProperties.class)
//public class TomcatWebServerConfigRefactor2 {
//
//    // 지금 등록하려는 Bean을 개발자가 등록한적이 있으면 등록되지 않도록한다.
//    @ConditionalOnMissingBean
//    @Bean(name = "tomcatWebServerFactory")
//    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//
//        factory.setContextPath(properties.getContextPath());
//        factory.setPort(properties.getPort());
//
//        return factory;
//    }
//
//}
