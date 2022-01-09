package io.github.shirohoo.reactive.app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {
    Item item1;
    Item item2;

    @BeforeEach
    void setUp() {
        item1 = Item.of("itemName", 1_000.0);
        item2 = Item.of("itemName", 1_000.0);
    }

    @Test
    void create() throws Exception {
        assertThat(item1.getName()).isEqualTo("itemName");
        assertThat(item1.getPrice()).isEqualTo(1_000.0);
    }

    @Test
    void equals() throws Exception {
        assertThat(item1).isEqualTo(item2);
    }
}
