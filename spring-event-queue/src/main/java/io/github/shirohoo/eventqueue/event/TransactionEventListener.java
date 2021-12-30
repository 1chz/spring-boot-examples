package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.adapter.persistence.TransactionRepository;
import io.github.shirohoo.eventqueue.domain.Transaction;
import io.github.shirohoo.eventqueue.domain.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionEventListener {
    private final TransactionEventQueue eventQueue;
    private final TransactionRepository repository;

    @EventListener
    @Async("threadPoolTaskExecutor")
    public void onEvent(TransactionEvent event) {
        Transaction transaction = event.getTransaction();
        while (isEventQueueFull(event)) {
            if (!transaction.isQueueWait()) {
                update(transaction, TransactionStatus.QUEUE_WAIT);
            }
        }
        update(transaction, TransactionStatus.QUEUE);
    }

    private boolean isEventQueueFull(TransactionEvent event) {
        return !eventQueue.offer(event);
    }

    private void update(Transaction transaction, TransactionStatus queue) {
        Transaction updatedTransaction = transaction.update(queue);
        repository.update(updatedTransaction);
    }
}
