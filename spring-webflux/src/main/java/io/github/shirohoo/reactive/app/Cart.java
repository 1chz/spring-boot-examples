package io.github.shirohoo.reactive.app;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
class Cart {
    @Id
    private String id;
    private List<CartItem> cartItems;

    Cart() {}

    private Cart(String id) {
        this(id, new ArrayList<>());
    }

    private Cart(String id, ArrayList<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    static Cart create(String id) {
        return new Cart(id);
    }
}
