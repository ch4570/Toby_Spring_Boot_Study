package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
*  DispatcherServlet 사용 & Factory Method 사용
* */
// @Configuration 애너테이션은 @Bean 애너테이션이 붙은 팩토리 메서드들이 모여있는 클래스라는 것을
// Spring Container가 알게해준다.
@Configuration
public class HellobootApplication2 {

	// Object를 만들어주는 메서드 = Factory Method
	// @Bean 애너테이션을 붙이면 Spring Container가 Factory Method를 사용하게 된다.
//	@Bean
//	public HelloController helloController(HelloService helloService) {
//		return new HelloController(helloService);
//	}

	@Bean
	public HelloService helloService(HelloRepository helloRepository) {
		return new SimpleHelloService(helloRepository);
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

		// registerBean을 이용해서 Bean을 생성하면 getBean() 메서드로 반환받을 수 있다.
//		applicationContext.registerBean(HelloController.class);
//		applicationContext.registerBean(SimpleHelloService.class);


		// Spring Container를 초기화해주어야 한다.
		applicationContext.register(HellobootApplication2.class);
		applicationContext.refresh();
	}

}
