package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.domain.Transaction;
import java.util.UUID;
import lombok.Value;

@Value
public class TransactionEvent {
    String id;
    Transaction transaction;

    /**
     * 이벤트 발생시 호출할 정적 팩토리 메서드.
     *
     * @param transaction STANDBY 상태의 유효한 Transaction 객체
     * @return TransactionEvent 인스턴스
     * @throws IllegalStateException Transaction의 상태가 STANDBY가 아닌 경우
     */
    public static TransactionEvent occurs(Transaction transaction) {
        if (transaction.isStandBy()) {
            return new TransactionEvent(UUID.randomUUID().toString(), transaction);
        }
        throw new IllegalStateException("Transaction status is not STANDBY!");
    }
}
