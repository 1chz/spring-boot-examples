package io.github.shirohoo.reactive.domain;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class Restaurant {
    private final Random picker;
    private final List<Dish> menu;

    public Restaurant() {
        this.picker = new Random();
        this.menu = Arrays.asList(
            Dish.newDish("sushi"),
            Dish.newDish("pizza"),
            Dish.newDish("BBQ"),
            Dish.newDish("noodle")
        );
    }

    public Flux<Dish> getDishes() {
        return Flux.<Dish>generate(synchronousSink -> synchronousSink.next(randomDish()))
            .delayElements(Duration.ofSeconds(2));
    }

    private Dish randomDish() {
        return menu.get(picker.nextInt(menu.size()));
    }
}
