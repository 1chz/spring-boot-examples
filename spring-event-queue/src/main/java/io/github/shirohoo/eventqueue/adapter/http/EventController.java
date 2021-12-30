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

    @GetMapping
    public void onEvent() {
        useCase.save(Transaction.create());
    }
}
