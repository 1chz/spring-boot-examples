# Open Feign

---

`RestTemplate`, `WebClient` 등을 대체할 수 있는 HTTP 클라이언트이다.

`JPA Repository` 와 `Spring Controller` 를 합쳐놓은 듯한 느낌으로 사용할 수 있다.

<br />

## 의존성

---

```groovy
// build.gradle
dependencyManagement {
    imports {
        mavenBom 'org.springframework.cloud:spring-cloud-dependencies:2020.0.3'
    }
}

dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'
}
```

<br />

## 기본설정

---

```java
@Configuration
@EnableFeignClients(basePackages = "io.github.shirohoo.openfeign.client") // OpenFeignClient를 활성화. 별도의 설정클래스에 추가했으므로 콤포넌트 스캔을 위해 베이스패키지를 지정
public class OpenFeignConfig {

    @Bean
    public Level feignLoggerLevel() {
        return Level.FULL; // OpenFeignClient 가 제공하는 모든 레벨의 로그를 사용
    }

    @Bean
    public Retryer retryer() {
        return new Default(1_000, 2_000, 3); // Error가 발생 할 경우 재시도에 대한 설정
    }

}
```

<br />

`Level.FULL` 설정 시 `OpenFeign`이 제공하는 기본 로거 구현체를 사용하기 위해서는 로깅 레벨을 `DEBUG`까지 허용해줘야 한다.

<br />

```yaml
# application.yaml
logging:
  level:
    # 이렇게 FeignClient가 위치한 패키지별로 로깅 레벨을 설정할수도 있다
    io.github.shirohoo.openfeign.client: DEBUG
```

<br />

```shell
i.g.s.c.FeignClient - [FeignClient#method] ---> GET https://jsonplaceholder.typicode.com/todos HTTP/1.1
i.g.s.c.FeignClient - [FeignClient#method] Content-Length: 277
i.g.s.c.FeignClient - [FeignClient#method] Content-Type: application/json
i.g.s.c.FeignClient - [FeignClient#method]
i.g.s.c.FeignClient - [FeignClient#method] {}
i.g.s.c.FeignClient - [FeignClient#method] ---> END HTTP (277-byte body)
i.g.s.c.FeignClient - [FeignClient#method] <--- HTTP/1.1 200 200 (737ms)
i.g.s.c.FeignClient - [FeignClient#method] connection: Keep-Alive
i.g.s.c.FeignClient - [FeignClient#method] content-type: application/json;charset=UTF-8
i.g.s.c.FeignClient - [FeignClient#method] date: Fri, 12 Nov 2021 03:51:08 GMT
i.g.s.c.FeignClient - [FeignClient#method] keep-alive: timeout=5, max=100
i.g.s.c.FeignClient - [FeignClient#method] server: Apache
i.g.s.c.FeignClient - [FeignClient#method] transfer-encoding: chunked
i.g.s.c.FeignClient - [FeignClient#method]
i.g.s.c.FeignClient - [FeignClient#method] {"data":"data"}
i.g.s.c.FeignClient - [FeignClient#method] <--- END HTTP (310-byte body)
```

## 클라이언트

---

```java
@FeignClient(
    name = "jsonPlaceHolder",
    url = "${feign.client.url.jsonPlaceHolder}", // application.yaml에 정의한 url을 표현식을 통해 참조할 수 있다
    configuration = {
        OpenFeignConfig.class, // 이전에 설정한 로그, 재시도 관련 설정을 사용하고 싶다면 탑재
        CustomErrorDecoder.class // 별도로 구현한 디코더를 탑재하여 에러 핸들링
    }
)
public interface JsonPlaceHolderClient { // JPA Repository와 마찬가지로 인터페이스로 생성한다

    @GetMapping("/posts")
    List<Post> getPosts();

    @GetMapping("/comments")
    List<Comment> getComment();

    @GetMapping("/albums")
    List<Album> getAlbums();

    @GetMapping("/photos")
    List<Photo> getPhotos();

    @GetMapping("/todos")
    List<Todo> getTodos();

    @GetMapping("/users")
    List<User> getUsers();

}
```

<br />

## 예외 핸들링

---

별도의 에러 핸들링이 필요하다면 `ErrorDecoder` 를 구현한다.

이후 Client 클래스에 구현한 디코더를 탑재하면 된다.

```java
// FeignClient 가 API 호출 중 발생하는 에러를 처리할 클래스
// Bean 으로 등록되어 있어야 동작한다
@Slf4j
@Component
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {
        // response 에서 데이터 추출 및 처리
        // 아래는 예제
        int status = response.status();
        final HttpStatus httpStatus = HttpStatus.valueOf(status);
        if (httpStatus.is1xxInformational()) {
            log.info("is1xxInformational");
        } else if (httpStatus.is2xxSuccessful()) {
            log.info("is2xxSuccessful");
        } else if (httpStatus.is3xxRedirection()) {
            log.info("is3xxRedirection");
        } else if (httpStatus.is4xxClientError()) {
            log.info("is4xxClientError");
        } else if (httpStatus.is5xxServerError()) {
            log.info("is5xxServerError");
        }
        return FeignException.errorStatus(methodKey, response);
    }

}
```

<br />

## 사용

---

JPA Repository와 사용법이 같다.

클라이언트를 DI하고 메소드를 호출한다.

<br />

```java
@SpringBootTest
class JsonPlaceHolderClientTest {

    @Autowired
    private JsonPlaceHolderClient client;

    @Test
    void getPosts() throws Exception {
        final List<Post> posts = client.getPosts();
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    void getComments() throws Exception {
        final List<Comment> posts = client.getComment();
        assertThat(posts.size()).isEqualTo(500);
    }

    @Test
    void getAlbums() throws Exception {
        final List<Album> posts = client.getAlbums();
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    void getPhotos() throws Exception {
        final List<Photo> posts = client.getPhotos();
        assertThat(posts.size()).isEqualTo(5000);
    }

    @Test
    void getTodos() throws Exception {
        final List<Todo> posts = client.getTodos();
        assertThat(posts.size()).isEqualTo(200);
    }

    @Test
    void getUsers() throws Exception {
        final List<User> posts = client.getUsers();
        assertThat(posts.size()).isEqualTo(10);
    }

}
```
