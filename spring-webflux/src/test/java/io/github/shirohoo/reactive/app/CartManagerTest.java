package io.github.shirohoo.reactive.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class CartManagerTest {
    @Mock
    ItemRepository itemRepository;

    @Mock
    CartRepository cartRepository;

    CartManager cartManager;

    @BeforeEach
    void setUp() {
        // ...given
        Item sampleItem = Item.of("sample item", 19.99);
        CartItem sampleCartItem = CartItem.create(sampleItem);
        Cart sampleCart = Cart.create("MyCart", Collections.singletonList(sampleCartItem));

        given(cartRepository.findById(anyString())).willReturn(Mono.empty());
        given(itemRepository.findById(anyString())).willReturn(Mono.just(sampleItem));
        given(cartRepository.save(any(Cart.class))).willReturn(Mono.just(sampleCart));

        cartManager = new CartManager(itemRepository, cartRepository);
    }

    @Test
    void addItemToCart() {
        cartManager.addItemToCart("MyCart", "sample item")
            .as(StepVerifier::create)
            .expectNextMatches(cart -> {
                assertThat(cart.getCartItems())
                    .extracting(CartItem::getQuantity)
                    .containsExactlyInAnyOrder(1);

                assertThat(cart.getCartItems())
                    .extracting(CartItem::getItem)
                    .containsExactly(Item.of("sample item", 19.99));

                return true;
            })
            .verifyComplete();
    }
}
