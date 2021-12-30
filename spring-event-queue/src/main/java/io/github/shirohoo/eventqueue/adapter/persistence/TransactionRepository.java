package io.github.shirohoo.eventqueue.adapter.persistence;

import io.github.shirohoo.eventqueue.domain.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Transaction update(Transaction transaction);
}
