![Java_8](https://img.shields.io/badge/java-8-red?logo=java)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v2.6.2-green.svg?logo=spring)
[![GitHub license](https://img.shields.io/github/license/TEAM-ARK/sprout-backend)](https://github.com/TEAM-ARK/sprout-backend)

<br />

- [📕 스프링 부트 실전 활용 마스터 - 그렉 턴키스트 지음, 오명운 옮김](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9791189909307)

![image](https://user-images.githubusercontent.com/71188307/148669388-aac14122-76af-4e6d-b6cc-810ed23718a8.png)

<br />

# 스프링 부트와 웹 플럭스

---

웹 플럭스는 리액티브 스트림을 이용한 비동기 처리를 지원한다.

리액티브 스트림이란 발행자(publisher)와 구독자(subscriber)간의 계약을 정의하는 명세로, 여기서 발행자는 서버, 구독자는 서버에 요청을 보내는 모든 클라이언트를 의미한다.

예를 들어, 클라이언트가 서버에 **xx에 대한 데이터 10개만 보내줘** 라고 서버에 요청하면, 서버는 정확히 10개의 데이터가 준비되는대로 돌려주는 것이다.

이러한 계약을 리액티브 스트림이라 한다.

<br />

## Spring Data MongoDB

## Query Methods

---

![image](https://user-images.githubusercontent.com/71188307/148669353-e197403b-6216-4675-947b-e1320dafa459.png)
![image](https://user-images.githubusercontent.com/71188307/148669357-0e5985d1-edb5-4d38-8730-6ef337591e44.png)

<br />

## Return types

---

![image](https://user-images.githubusercontent.com/71188307/148669359-67c09bdc-2dea-4338-bc1e-286f7d02e98c.png)

<br />

## Trade-Off

---

![image](https://user-images.githubusercontent.com/71188307/148669371-8d363ac1-66f7-4b72-bd03-c85660270602.png)


## BlockHound

---

블로킹 API 호출을 감지하는 자바 에이전트이다.

자바 에이전트란 자바가 실행되기 전 먼저 실행되어(premain) 애플리케이션의 바이트 코드를 조작하는 객체이다.

<br />

```groovy
// build.gradle
dependencies{
    implementation'io.projectreactor.tools:blockhound:1.0.6.RELEASE'
}
```

<br />

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        BlockHound.install(); // 추가
        SpringApplication.run(Application.class, args);
    }
}

```

<br />

애플리케이션을 실행하고 `localhost:8080`으로 진입하면 블록하운드는 블로킹 API가 호출됐음을 감지하고 알려준다.

<br />

![image](https://user-images.githubusercontent.com/71188307/148671222-439b18fb-85b9-489c-99b3-a18a01e3a5cf.JPG)

<br />

이러한 블로킹 API를 감수할 수 있다면 블록하운드가 해당 블로킹 API를 감지하지 않도록 허용(allow)해주면 된다.

<br />

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        BlockHound.builder()
            .allowBlockingCallsInside(TemplateEngine.class.getCanonicalName(), "process")
            .allowBlockingCallsInside(FileInputStream.class.getCanonicalName(), "readBytes")
            .install();

        SpringApplication.run(Application.class, args);
    }
}
```

<br />
