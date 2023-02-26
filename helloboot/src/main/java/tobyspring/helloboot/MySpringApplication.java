package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {

    public static void run(Class<?> applicationClass, String... args) {
        // Spring Container는 applicationContext로 구현이 되어있는데, ApplicationContext는 구현체가 다양하다.
        // Template Method 패턴은 상속을 통해 기능을 확장해 나가는 패턴이다.
        // 대표적으로 ApplicationContext가 이 패턴을 사용중이다.
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                // 이 코드는 생략이 불가능하다.
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                // Spring Container를 지정한다.
                // Spring Container를 직접 지정하지 않아도 Spring이 넣어주기 때문에 아래 코드가 생략이 가능하다.
                // DispatcherServlet은 ApplicationAware 인터페이스를 구현했기 때문에 가능하다.
//				dispatcherServlet.setApplicationContext(this);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {

                    // DispatcherServlet을 등록한다.
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*");
                });
                webServer.start();
            }
        };

        applicationContext.register(applicationClass);
        applicationContext.refresh();
    }
}
