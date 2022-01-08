package io.github.shirohoo.reactive.v2;

import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
class Item {
    @Id
    String id;
    String name;
    double price;

    private Item(String name, double price) {
        this.id = null;
        this.name = name;
        this.price = price;
    }

    static Item of(String name, double price) {
        return new Item(name, price);
    }
}
