![Java_8](https://img.shields.io/badge/java-8-red?logo=java)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v2.6.2-green.svg?logo=spring)
[![GitHub license](https://img.shields.io/github/license/TEAM-ARK/sprout-backend)](https://github.com/TEAM-ARK/sprout-backend)

# 스프링 부트와 웹 플럭스

---

웹 플럭스는 리액티브 스트림을 이용한 비동기 처리를 지원한다.

리액티브 스트림이란 발행자(publisher)와 구독자(subscriber)간의 계약을 정의하는 명세로, 여기서 발행자는 서버, 구독자는 서버에 요청을 보내는 모든 클라이언트를 의미한다.

예를 들어, 클라이언트가 서버에 **xx에 대한 데이터 10개만 보내줘** 라고 서버에 요청하면, 서버는 정확히 10개의 데이터가 준비되는대로 돌려주는 것이다.

이러한 계약을 리액티브 스트림이라 한다.
