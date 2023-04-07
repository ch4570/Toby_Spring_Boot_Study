package tobyspring.helloboot;

/*
*  DisPatcherServlet과 ServletServerFactory를 Spring이 관리하도록 변경
* */

//@Configuration

// @ComponentScan을 붙이면 패키지 레벨에서 동일 레벨과 하위를 검색하면서 @Component가 붙어있는 클래스들을 Bean으로 등록해준다.
//@ComponentScan

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class HellobootApplication4 {

	private final JdbcTemplate jdbcTemplate;

	public HellobootApplication4(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@PostConstruct
	void init() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}


	// 자동 등록된 구성 정보들이 어떤 것들이 있는지 콘솔에 찍는코드
	@Bean
	ApplicationRunner run(ConditionEvaluationReport report) {
		return args -> {
			System.out.println(report.getConditionAndOutcomesBySource().entrySet().stream()
					.filter(co -> co.getValue().isFullMatch())
					.filter(co -> !co.getKey().contains("Jmx"))
					.map(co -> {
						System.out.println(co.getKey());
						co.getValue().forEach(c -> {
							System.out.println("\t" + c.getOutcome());
						});
						System.out.println();
						return co;
					}).count());
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication4.class, args);
	}

}
