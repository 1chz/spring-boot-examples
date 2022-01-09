package io.github.shirohoo.reactive.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
class CartController {
    private final CartManager cartManager;

    @GetMapping
    Mono<Rendering> viewCart() {
        return cartManager.viewCart();
    }

    @GetMapping("/items")
    Mono<Rendering> search(
        @RequestParam(required = false) String name,
        @RequestParam boolean useAnd
    ) {
        return cartManager.viewCart(name, useAnd);
    }

    @PostMapping("/items/{itemId}")
    Mono<String> addToCart(@PathVariable String itemId) {
        Mono<Cart> cart = cartManager.findByCartId("MyCart");
        return cartManager.addToACart(cart, itemId)
            .thenReturn("redirect:/carts");
    }
}
