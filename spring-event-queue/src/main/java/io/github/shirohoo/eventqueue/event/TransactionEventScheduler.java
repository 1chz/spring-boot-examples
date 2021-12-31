package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.adapter.persistence.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionEventScheduler {
    private final TransactionEventQueue eventQueue;
    private final TransactionRepository repository;

    @Async("taskScheduler")
    @Scheduled(fixedRate = 100)
    public void schedule() {
        new TransactionEventWorker(eventQueue, repository)
            .run();
    }
}
