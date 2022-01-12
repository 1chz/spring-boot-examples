package io.github.shirohoo.reactive.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
class Cart {
    @Id
    private String id;
    private List<CartItem> cartItems;

    protected Cart() {}

    private Cart(String id) {
        this(id, new ArrayList<>());
    }

    private Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public static Cart create(String id) {
        return new Cart(id);
    }

    public static Cart create(String id, List<CartItem> cartItems) {
        return new Cart(id, cartItems);
    }

    public Cart removeItems() {
        this.cartItems = cartItems.stream()
            .filter(CartItem::isGreaterThanOneQuantity)
            .collect(Collectors.toList());

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cart cart = (Cart) o;
        return Objects.equals(getId(), cart.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
