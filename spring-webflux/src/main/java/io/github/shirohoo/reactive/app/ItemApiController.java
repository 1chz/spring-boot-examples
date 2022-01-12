package io.github.shirohoo.reactive.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemApiController {
    private final ItemRepository itemRepository;

    @GetMapping
    public Flux<Item> findAll() {
        return itemRepository.findAll();
    }

    @GetMapping("/{itemId}")
    public Mono<Item> findOne(@PathVariable String itemId) {
        return itemRepository.findById(itemId);
    }

    @PostMapping
    public Mono<ResponseEntity<Item>> addItem(@RequestBody Mono<Item> itemMono) {
        return itemMono
            .flatMap(itemRepository::save)
            .map(savedItem -> ResponseEntity
                .created(URI.create("/api/items/" + savedItem.getId()))
                .body(savedItem));
    }

    @PutMapping("/{itemId}")
    public Mono<ResponseEntity<?>> updateItem(
        @PathVariable String itemId,
        @RequestBody Mono<Item> itemMono
    ) {
        return itemMono
            .map(item -> Item.update(itemId, item.getName(), item.getPrice()))
            .flatMap(itemRepository::save)
            .map(ResponseEntity::ok);
    }
}
