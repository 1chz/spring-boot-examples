package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public void publish(Transaction transaction) {
        publisher.publishEvent(transaction);
    }
}
