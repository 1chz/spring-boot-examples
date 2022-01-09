package io.github.shirohoo.reactive.app;

import java.util.Objects;
import lombok.Getter;

@Getter
class CartItem {
    private Item item;
    private int quantity;

    protected CartItem() {}

    private CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public static CartItem create(Item item) {
        return of(item, 1);
    }

    public static CartItem of(Item item, int quantity) {
        return new CartItem(item, quantity);
    }

    public void increment() {
        this.quantity++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CartItem cartItem = (CartItem) o;
        return getQuantity() == cartItem.getQuantity() && Objects.equals(getItem(), cartItem.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem(), getQuantity());
    }
}
