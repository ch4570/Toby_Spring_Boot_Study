package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

    @Test
    void helloApi() {

        // http -v ":8080/hello?name=Spring"

        // http -v 명령어로 응답을 확인하던 제품(도구)의 이름은 Httpie이다.

        // RestTemplate에 비해 테스트에 용이하다.
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // 응답 검증은 3가지로 진행

        // status code 200 인지 검증
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        // header(content-type) 이 text/plain 인지
        assertThat(result.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);

        // body Hello Spring 이라는 내용을 가지고 있는지
        assertThat(result.getBody()).isEqualTo("*Hello Spring*");

    }

    @Test
    void failsHelloApi() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        // status code 500 인지 검증
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
