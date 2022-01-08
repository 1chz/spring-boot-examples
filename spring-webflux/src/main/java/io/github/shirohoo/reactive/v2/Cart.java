package io.github.shirohoo.reactive.v2;

import java.util.ArrayList;
import java.util.List;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
class Cart {
    @Id
    String id;
    List<CartItem> cartItems;

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
