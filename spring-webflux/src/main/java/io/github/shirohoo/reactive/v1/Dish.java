package io.github.shirohoo.reactive.v1;

import lombok.Value;

@Value
class Dish {
    String name;
    boolean delivered;

    private Dish(String name) {
        this.name = name;
        this.delivered = false;
    }

    private Dish(String name, boolean delivered) {
        this.name = name;
        this.delivered = delivered;
    }

    public static Dish newDish(String name) {
        return new Dish(name);
    }

    public Dish deliver() {
        return new Dish(this.getName(), true);
    }

    public boolean isDelivered() {
        return delivered;
    }
}
