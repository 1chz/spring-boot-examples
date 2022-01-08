package io.github.shirohoo.reactive.v2;

import lombok.Value;

@Value
class CartItem {
    Item item;
    int quantity;

    private CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    static CartItem of(Item item, int quantity) {
        return new CartItem(item, quantity);
    }
}
