package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 	Servlet Container를 직접 관리하고, Spring Container를 사용하는 클래스
*   DispatcherServlet 사용 이전
* */
public class HellobootApplication {

	public static void main(String[] args) {


		GenericApplicationContext applicationContext = new GenericApplicationContext();

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

			// frontcontroller라는 이름의 서블릿을 등록해주고, HttpServlet을 익명 클래스를 이용해 객체를 생성한다.
			// Servlet이 아닌 HttpServlet으로 객체를 생성해야 필요한 메서드만 오버라이딩해서 사용이 가능하다.
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어 처리, 공통 기능

					// Mapping -> 요청에 들어있는 정보를 활용하여 어떤 메서드가 요청을 처리할지 결정하는 작업
					// "/hello" 라는 URL로 GET 요청이 왔을때 요청을 처리한다.
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {

						// Request 객체에서 파라미터(쿼리 스트링의 값)의 값을 빼내준다.
						String name = req.getParameter("name");

						// 등록된 Bean을 applicationContext로 부터 얻어온다.
						HelloController helloController = applicationContext.getBean(HelloController.class);

						// 받아온 name을 helloController의 hello 메서드가 가공해서 문자열로 반환해준다.
						String ret = helloController.hello(name);

						// 상태코드는 숫자로 200을 써도 올바르게 동작하지만, 문자열을 오타의 위험성이 있기 때문에 Enum으로 정의된 값을 사용하는 것이 좋다
						// 서블릿 컨테이너가 에러가 발생하지 않으면 일반적으로 200번을 보내기때문에 생략할 수 있다.
						resp.setStatus(HttpStatus.OK.value());

						// 헤더의 값을 써줄때도 문자열보다는 Enum으로 이루어진 값을 사용하는 것이 좋다.
						// setHeader 보다는 setContentType을 이용하면 코드를 개선할 수 있다.
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);

						// PrintWriter를 얻어와서 ret(만들어진 문자열)을 화면에 출력해준다.
						// PrintWriter는 text/plain 타입의 요청을 처리할때 유용하게 사용할 수 있다.
						resp.getWriter().println(ret);

					}
					else {
						// if ~ else if 블럭을 거치면서 요청에 맞는 URL이 아니라면 404 에러를 발생시킨다.
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
				// "/*" 의 의미는 모든 요청을 이 FrontController가 받아서 어떤 서블릿이 처리할지 결정하겠다는 뜻이다.
			}).addMapping("/*");
		});

		webServer.start();
	}

}
