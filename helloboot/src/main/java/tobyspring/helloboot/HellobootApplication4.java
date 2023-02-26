package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
*  DisPatcherServlet과 ServletServerFactory를 Spring이 관리하도록 변경
* */
@Configuration

// @ComponentScan을 붙이면 패키지 레벨에서 동일 레벨과 하위를 검색하면서 @Component가 붙어있는 클래스들을 Bean으로 등록해준다.
@ComponentScan
public class HellobootApplication4 {

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}


	public static void main(String[] args) {

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

		applicationContext.register(HellobootApplication4.class);
		applicationContext.refresh();
	}

}
