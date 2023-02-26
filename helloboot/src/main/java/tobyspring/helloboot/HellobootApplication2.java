package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*  DispatcherServlet 사용
* */
public class HellobootApplication2 {

	public static void main(String[] args) {


		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();

		// registerBean을 이용해서 Bean을 생성하면 getBean() 메서드로 반환받을 수 있다.
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);

		// Spring Container를 초기화해주어야 한다.
		applicationContext.refresh();

		// 대표적인 서블릿 컨테이너는 톰캣이다 serverFactory를 이용하는 이유는 다양한 구현체(Jetty 등)를 사용할때 인스턴스 교체가 쉽기 때문이다.
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		// 톰캣을 구동할 수 있는 WebServer 클래스를 serverFactory를 통해 생성한다.
		// getWebServer 메서드는 ServletContext를 매개변수로 받는데, serverFactory는 함수형 인터페이스이므로 람다식으로 대체가 가능하다.
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			// DispatcherServlet을 등록한다.
			servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(applicationContext)
			).addMapping("/*");
		});

		webServer.start();
	}

}
