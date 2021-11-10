package io.github.shirohoo.openfeign.config;

import feign.Logger.Level;
import feign.Retryer;
import feign.Retryer.Default;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "io.github.shirohoo.openfeign.client")
public class OpenFeignConfig {

    @Bean
    public Level feignLoggerLevel() {
        return Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        return new Default(1_000, 2_000, 3);
    }

}
