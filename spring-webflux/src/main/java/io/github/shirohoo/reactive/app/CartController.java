package io.github.shirohoo.reactive.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carts")
class CartController {
    private final CartManager cartManager;

    @GetMapping
    public Mono<Rendering> viewCart() {
        return cartManager.viewCart();
    }

    @GetMapping("/items")
    public Mono<Rendering> search(
        @RequestParam(required = false) String itemName,
        @RequestParam boolean useAnd
    ) {
        return cartManager.viewCart(itemName, useAnd);
    }

    @PostMapping("/items/{itemId}")
    public Mono<String> addToCart(@PathVariable String itemId) {
        return cartManager.addItemToCart("MyCart", itemId)
            .thenReturn("redirect:/carts");
    }

    @DeleteMapping("/items/{itemId}")
    public Mono<String> deleteFromCart(@PathVariable String itemId) {
        return cartManager.deleteFromCart("MyCart", itemId)
            .thenReturn("redirect:/carts");
    }
}
