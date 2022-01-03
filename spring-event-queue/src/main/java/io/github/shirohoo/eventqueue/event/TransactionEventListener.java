package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.adapter.persistence.TransactionRepository;
import io.github.shirohoo.eventqueue.domain.Transaction;
import io.github.shirohoo.eventqueue.domain.Transaction.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionEventListener {
    private final TransactionEventQueue eventQueue;
    private final TransactionRepository repository;

    @EventListener
    public void onEvent(Transaction transaction) {
        if (!transaction.isStandBy()) {
            log.info("Transaction(id:{}) status is not STANDBY!", transaction.getId());
            return;
        }

        while (eventQueue.isFull()) {
            if (!transaction.isQueueWait()) {
                transaction = updateStatus(transaction, TransactionStatus.QUEUE_WAIT);
            }
        }
        transaction = updateStatus(transaction, TransactionStatus.QUEUE);
        eventQueue.offer(transaction);
    }

    private Transaction updateStatus(Transaction transaction, TransactionStatus status) {
        TransactionStatus beforeStatus = transaction.getStatus();
        Transaction updatedTransaction = transaction.update(status);
        log.info("{\"transactionId\": {},\"before\":\"{}\", \"after\":\"{}\"}", transaction.getId(), beforeStatus, status);
        return repository.update(updatedTransaction);
    }
}
