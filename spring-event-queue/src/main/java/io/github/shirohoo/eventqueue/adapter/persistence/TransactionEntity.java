package io.github.shirohoo.eventqueue.adapter.persistence;

import io.github.shirohoo.eventqueue.domain.Transaction;
import io.github.shirohoo.eventqueue.domain.TransactionStatus;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(transaction.getId(), transaction.getStatus());
    }

    public Transaction toTransaction() {
        return Transaction.of(id, status);
    }

    public TransactionEntity update(Transaction transaction){
        this.status = transaction.getStatus();
        return this;
    }
}
