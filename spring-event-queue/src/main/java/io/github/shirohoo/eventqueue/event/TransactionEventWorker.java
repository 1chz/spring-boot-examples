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
        if (eventQueue.isRemaining()) {
            Transaction transaction = eventQueue.poll();
            try {
                transaction = updateStatus(transaction, TransactionStatus.PROGRESS);
                processing(1_000);
                successOrFailure(transaction);
            } catch (Exception e) {
                handlingInCaseOfFailure(transaction);
                log.error(e.getMessage());
            }
        }
    }

    private void processing(int processingTimeInMs) {
        try {
            Thread.sleep(processingTimeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void successOrFailure(Transaction transaction) {
        if (Math.random() < 0.5) {
            updateStatus(transaction, TransactionStatus.SUCCESS);
        } else {
            updateStatus(transaction, TransactionStatus.FAILURE);
        }
    }

    private void handlingInCaseOfFailure(Transaction transaction) {
        updateStatus(transaction, TransactionStatus.FAILURE);
    }

    private Transaction updateStatus(Transaction transaction, TransactionStatus status) {
        TransactionStatus beforeStatus = transaction.getStatus();
        Transaction updatedTransaction = transaction.update(status);
        log.info("{\"transactionId\": {},\"before\":\"{}\", \"after\":\"{}\"}", transaction.getId(), beforeStatus, status);
        return repository.update(updatedTransaction);
    }
}
