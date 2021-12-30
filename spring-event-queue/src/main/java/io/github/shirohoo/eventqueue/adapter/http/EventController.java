package io.github.shirohoo.eventqueue.adapter.http;

import io.github.shirohoo.eventqueue.domain.Transaction;
import io.github.shirohoo.eventqueue.usecase.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final TransactionUseCase useCase;

    @GetMapping("/transaction")
    public String transaction() {
        Transaction transaction = useCase.save(Transaction.create());
        return "Request complete! id: " + transaction.getId() + " status: " + transaction.getStatus();
    }

    @GetMapping("/transactions")
    public void transactions() {
        for (int i = 0; i < 50; i++) {
            useCase.save(Transaction.create());
        }
    }
}
