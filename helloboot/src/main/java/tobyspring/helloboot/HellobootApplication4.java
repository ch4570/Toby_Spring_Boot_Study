package tobyspring.helloboot;

/*
*  DisPatcherServlet과 ServletServerFactory를 Spring이 관리하도록 변경
* */

//@Configuration

// @ComponentScan을 붙이면 패키지 레벨에서 동일 레벨과 하위를 검색하면서 @Component가 붙어있는 클래스들을 Bean으로 등록해준다.
//@ComponentScan

import org.springframework.boot.SpringApplication;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication4 {


	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication4.class, args);
	}

}
