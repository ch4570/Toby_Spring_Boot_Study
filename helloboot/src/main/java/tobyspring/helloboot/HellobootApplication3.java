package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
*  애너테이션을 이용한 Bean 주입
* */
@Configuration

// @ComponentScan을 붙이면 패키지 레벨에서 동일 레벨과 하위를 검색하면서 @Component가 붙어있는 클래스들을 Bean으로 등록해준다.
@ComponentScan
public class HellobootApplication3 {


	public static void main(String[] args) {

		// Spring Container는 applicationContext로 구현이 되어있는데, ApplicationContext는 구현체가 다양하다.
		// Template Method 패턴은 상속을 통해 기능을 확장해 나가는 패턴이다.
		// 대표적으로 ApplicationContext가 이 패턴을 사용중이다.
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				// 이 코드는 생략이 불가능하다.
				super.onRefresh();

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

				WebServer webServer = serverFactory.getWebServer(servletContext -> {

					// DispatcherServlet을 등록한다.
					servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(this))
							.addMapping("/*");
				});
				webServer.start();
			}
		};

		applicationContext.register(HellobootApplication3.class);
		applicationContext.refresh();
	}

}
