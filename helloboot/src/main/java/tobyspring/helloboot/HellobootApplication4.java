package tobyspring.helloboot;

/*
*  DisPatcherServlet과 ServletServerFactory를 Spring이 관리하도록 변경
* */

//@Configuration

// @ComponentScan을 붙이면 패키지 레벨에서 동일 레벨과 하위를 검색하면서 @Component가 붙어있는 클래스들을 Bean으로 등록해준다.
//@ComponentScan

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication4 {


	@Bean
	ApplicationRunner applicationRunner(Environment env) {
		return args -> {
			String name = env.getProperty("my.name");
			System.out.println("my.name = " + name);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication4.class, args);
	}

}
