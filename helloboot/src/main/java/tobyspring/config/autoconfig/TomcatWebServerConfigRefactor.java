package tobyspring.config.autoconfig;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

//@MyAutoConfiguration
//@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfigRefactor {

    // Spring에서 사용하는 후처리기를 등록해야만 사용이 가능하다 -> PropertySourcesPlaceholderConfigurer @Bean 등록 필요
    @Value("${contextPath:}")
    String contextPath;

    // 콜론으로 default 값을 넣어줄 수 있다.
    @Value("${port:9090}")
    int port;

    // 지금 등록하려는 Bean을 개발자가 등록한적이 있으면 등록되지 않도록한다.
//    @ConditionalOnMissingBean
//    @Bean(name = "tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        factory.setContextPath(this.contextPath);
        factory.setPort(port);

        return factory;
    }



}
