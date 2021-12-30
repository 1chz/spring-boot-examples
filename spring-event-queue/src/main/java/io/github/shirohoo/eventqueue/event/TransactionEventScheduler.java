package io.github.shirohoo.eventqueue.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.shirohoo.eventqueue.adapter.persistence.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class TransactionEventScheduler {
    private final ObjectMapper objectMapper;
    private final TransactionEventQueue eventQueue;
    private final TransactionRepository repository;

    @Scheduled(fixedDelay = 500)
    public void eventProcessing() {
        new Thread(new TransactionEventWorker(objectMapper, eventQueue, repository))
            .start();
    }
}
