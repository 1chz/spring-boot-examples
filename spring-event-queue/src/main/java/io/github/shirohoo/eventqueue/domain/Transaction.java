package io.github.shirohoo.eventqueue.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Transaction {
    Long id;
    TransactionStatus status;

    public static Transaction create() {
        return Transaction.of(null, TransactionStatus.STANDBY);
    }

    public Transaction update(TransactionStatus status) {
        return Transaction.of(id, status);
    }

    public boolean isQueueWait() {
        return status == TransactionStatus.QUEUE_WAIT;
    }
}
