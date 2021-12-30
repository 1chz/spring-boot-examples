package io.github.shirohoo.eventqueue.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.shirohoo.eventqueue.adapter.persistence.TransactionRepository;
import io.github.shirohoo.eventqueue.domain.Transaction;
import io.github.shirohoo.eventqueue.domain.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TransactionEventWorker implements Runnable {
    private final ObjectMapper objectMapper;
    private final TransactionEventQueue eventQueue;
    private final TransactionRepository repository;

    @Override
    public void run() {
        if (eventQueue.size() > 0) {
            TransactionEvent event = eventQueue.poll();
            Transaction transaction = update(event.getTransaction(), TransactionStatus.PROGRESS);
            processing(1_000, transaction);
            successOrFailure(transaction);
            return;
        }
        eventQueue.healthCheck();
    }

    private void processing(int processingTimeInMs, Transaction transaction) {
        info(transaction);
        try {
            Thread.sleep(processingTimeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void successOrFailure(Transaction transaction) {
        if (Math.random() < 0.5) {
            transaction = update(transaction, TransactionStatus.SUCCESS);
        } else {
            transaction = update(transaction, TransactionStatus.FAILURE);
        }
        info(transaction);
    }

    private Transaction update(Transaction transaction, TransactionStatus status) {
        Transaction updatedTransaction = transaction.update(status);
        return repository.update(updatedTransaction);
    }

    private void info(Transaction transaction) {
        try {
            log.info(objectMapper.writeValueAsString(transaction));
        } catch (JsonProcessingException e) {
            // doSomething...
        }
    }
}
