package io.github.shirohoo.reactive.app;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<?>> addItem(
        @PathVariable String itemId,
        @RequestBody Mono<Item> itemMono
    ) {
        return itemMono
            .map(item -> Item.update(itemId, item.getName(), item.getPrice()))
            .flatMap(itemRepository::save)
            .map(ResponseEntity::ok);
    }
}
