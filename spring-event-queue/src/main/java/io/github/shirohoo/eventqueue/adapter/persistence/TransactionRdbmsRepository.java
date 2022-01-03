package io.github.shirohoo.eventqueue.adapter.persistence;

import io.github.shirohoo.eventqueue.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionRdbmsRepository implements TransactionRepository {
    private final TransactionJpaRepository repository;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        return repository.save(TransactionEntity.from(transaction))
            .toTransaction();
    }

    @Override
    @Transactional
    public Transaction update(Transaction transaction) {
        return repository.findById(transaction.getId())
            .orElseThrow()
            .update(transaction)
            .toTransaction();
    }
}
