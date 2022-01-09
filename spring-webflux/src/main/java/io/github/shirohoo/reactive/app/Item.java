package io.github.shirohoo.reactive.app;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
class Item {
    @Id
    private String id;
    private String name;
    private double price;

    Item() {}

    private Item(String name, double price) {
        this.id = null;
        this.name = name;
        this.price = price;
    }

    static Item of(String name, double price) {
        return new Item(name, price);
    }
}
