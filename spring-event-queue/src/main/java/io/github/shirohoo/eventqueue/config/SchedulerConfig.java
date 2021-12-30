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
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(processors);
        taskScheduler.setThreadNamePrefix("Scheduler-Thread-");
        taskScheduler.initialize();
        return taskScheduler;
    }
}
