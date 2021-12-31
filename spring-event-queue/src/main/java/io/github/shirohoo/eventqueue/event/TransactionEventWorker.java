package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.adapter.persistence.TransactionRepository;
import io.github.shirohoo.eventqueue.domain.Transaction;
import io.github.shirohoo.eventqueue.domain.Transaction.TransactionStatus;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TransactionEventWorker implements Runnable {
    private final TransactionEventQueue eventQueue;
    private final TransactionRepository repository;

    @Override
    @Transactional
    public void run() {
        if (eventQueue.size() > 0) {
            TransactionEvent event = eventQueue.poll();
            Transaction transaction = update(event.getTransaction(), TransactionStatus.PROGRESS);
            eventQueue.healthCheck(event, transaction.getStatus());
            processing(1_000);
            transaction = successOrFailure(transaction);
            eventQueue.healthCheck(event, transaction.getStatus());
            return;
        }
        eventQueue.healthCheck();
    }

    private void processing(int processingTimeInMs) {
        try {
            Thread.sleep(processingTimeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Transaction successOrFailure(Transaction transaction) {
        if (Math.random() < 0.5) {
            return update(transaction, TransactionStatus.SUCCESS);
        } else {
            return update(transaction, TransactionStatus.FAILURE);
        }
    }

    private Transaction update(Transaction transaction, TransactionStatus status) {
        Transaction updatedTransaction = transaction.update(status);
        return repository.update(updatedTransaction);
    }
}
