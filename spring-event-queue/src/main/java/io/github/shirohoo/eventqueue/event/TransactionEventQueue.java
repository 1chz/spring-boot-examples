package io.github.shirohoo.eventqueue.event;

import io.github.shirohoo.eventqueue.domain.TransactionStatus;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionEventQueue {
    private final Queue<TransactionEvent> queue;
    private final int queueSize;

    private TransactionEventQueue(int size) {
        this.queueSize = size;
        this.queue = new LinkedBlockingQueue<>(size);
    }

    public static TransactionEventQueue of(int size) {
        return new TransactionEventQueue(size);
    }

    public boolean offer(TransactionEvent event) {
        if (queue.offer(event)) {
            log.info(getMessage(event, TransactionStatus.QUEUE));
            return true;
        }
        log.info(getMessage(event, TransactionStatus.QUEUE_WAIT));
        return false;
    }


    public TransactionEvent poll() {
        if (queue.size() == 0) {
            throw new IllegalStateException("No events in the queue !");
        }
        TransactionEvent returnValue = queue.poll();
        log.info(getMessage(returnValue, TransactionStatus.PROGRESS));
        return returnValue;
    }

    public int size() {
        return queue.size();
    }

    public void healthCheck() {
        log.info("{\"totalQueueSize\": " + queueSize + ", \"currentQueueSize\": " + size() + "\"}");
    }

    private String getMessage(TransactionEvent event, TransactionStatus status) {
        return "{\"totalQueueSize\": " + queueSize +
            ", \"currentQueueSize\": " + size() +
            ", \"transactionId\": " + event.getTransaction().getId() +
            ", \"state\": \"" + status +
            "\"}";
    }
}
