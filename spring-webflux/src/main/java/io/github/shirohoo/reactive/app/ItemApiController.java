package io.github.shirohoo.reactive.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemApiController {
    private final ItemRepository itemRepository;

    @GetMapping
    public Flux<Item> findAll() {
        return itemRepository.findAll();
    }
}
