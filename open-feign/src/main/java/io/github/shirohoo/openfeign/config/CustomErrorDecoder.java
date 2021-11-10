package io.github.shirohoo.openfeign.config;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


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
