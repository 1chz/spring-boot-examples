package io.github.shirohoo.reactive.app;

import java.util.Objects;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
class Item {
    @Id
    private String id;
    private String name;
    private double price;

    protected Item() {}

    private Item(String name, double price) {
        this(null, name, price);
    }

    private Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Item of(String name, double price) {
        return new Item(name, price);
    }

    public static Item update(String id, String name, double price) {
        return new Item(id, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Double.compare(item.getPrice(), getPrice()) == 0 && Objects.equals(getId(), item.getId()) && Objects.equals(getName(), item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }
}
