package io.github.shirohoo.eventqueue.domain;

import lombok.Value;

/**
 * 결제거래를 표현하는 클래스.
 */
@Value(staticConstructor = "of")
public class Transaction {
    Long id;
    TransactionStatus status;

    /**
     * 새로운 결제거래가 생성되어야 할 경우 호출해야 하는 정적 팩토리 메서드.
     * @return STANDBY 상태의 인스턴스를 반환
     */
    public static Transaction create() {
        return Transaction.of(null, TransactionStatus.STANDBY);
    }

    /**
     * 객체의 상태가 업데이트되야 할 경우 새로운 객체를 생성하여 반환한다
     * @param status 업데이트되어야 할 상태
     * @return 상태가 업데이트된 새로운 인스턴스
     */
    public Transaction update(TransactionStatus status) {
        return Transaction.of(id, status);
    }

    /**
     * 객체의 상태가 STANDBY 인지?
     * @return STANDBY라면 true를 반환
     */
    public boolean isStandBy(){
        return status == TransactionStatus.STANDBY;
    }

    /**
     * 객체의 상태가 QUEUE_WAIT 인지?
     * @return QUEUE_WAIT이라면 true를 반환
     */
    public boolean isQueueWait() {
        return status == TransactionStatus.QUEUE_WAIT;
    }

    /**
     * 결제 거래의 상태를 나타내는 enum 클래스
     */
    public enum TransactionStatus {
        STANDBY,
        QUEUE_WAIT,
        QUEUE,
        PROGRESS,
        SUCCESS,
        FAILURE
    }
}
