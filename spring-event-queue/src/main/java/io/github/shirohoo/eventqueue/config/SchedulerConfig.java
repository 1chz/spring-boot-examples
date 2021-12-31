package io.github.shirohoo.eventqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

        // 스케쥴러 스레드풀의 사이즈. 여기서는 머신의 프로세서 수로 하였다.
        taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());

        // 로그에 찍힐 스케쥴러 스레드의 접두사
        taskScheduler.setThreadNamePrefix("Scheduler-Thread-");

        // 모든 설정을 적용하고 ThreadPoolTaskScheduler를 초기화
        taskScheduler.initialize();

        return taskScheduler;
    }
}
