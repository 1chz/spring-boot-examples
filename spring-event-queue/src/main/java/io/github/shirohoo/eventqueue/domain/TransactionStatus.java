package io.github.shirohoo.eventqueue.domain;

public enum TransactionStatus {
    STANDBY,
    QUEUE_WAIT,
    QUEUE,
    PROGRESS,
    SUCCESS,
    FAILURE
}
