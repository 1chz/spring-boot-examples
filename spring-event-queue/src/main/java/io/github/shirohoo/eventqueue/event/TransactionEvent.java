package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.domain.Transaction;
import java.util.UUID;
import lombok.Value;

@Value
public class TransactionEvent {
    String id;
    Transaction transaction;

    public static TransactionEvent occurs(Transaction transaction) {
        return new TransactionEvent(UUID.randomUUID().toString(), transaction);
    }
}
