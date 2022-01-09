package io.github.shirohoo.reactive.app;

import lombok.Getter;

@Getter
class CartItem {
    private Item item;
    private int quantity;

    CartItem() {}

    private CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    static CartItem create(Item item) {
        return of(item, 0);
    }

    static CartItem of(Item item, int quantity) {
        return new CartItem(item, quantity);
    }

    public void increment() {
        this.quantity++;
    }
}
