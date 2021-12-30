package io.github.shirohoo.eventqueue.adapter.persistence;

import io.github.shirohoo.eventqueue.domain.Transaction;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
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
