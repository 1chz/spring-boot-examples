package io.github.shirohoo.eventqueue.usecase;

import io.github.shirohoo.eventqueue.adapter.persistence.TransactionRepository;
import io.github.shirohoo.eventqueue.domain.Transaction;
import io.github.shirohoo.eventqueue.event.TransactionEvent;
import io.github.shirohoo.eventqueue.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionUseCase {
    private final EventPublisher publisher;
    private final TransactionRepository repository;

    public void save(Transaction transaction) {
        Transaction savedTransaction = repository.save(transaction);
        log.info("Create new transaction! {}", savedTransaction);
        publisher.publish(TransactionEvent.occurs(savedTransaction));
    }
}
