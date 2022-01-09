package io.github.shirohoo.reactive.app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataMongoTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void itemRepositorySavesItems() throws Exception {
        Item sampleItem = Item.of("item1", 19.99);

        itemRepository.save(sampleItem)
            .as(StepVerifier::create)
            .expectNextMatches(item -> {
                assertThat(item.getId()).isNotNull();
                assertThat(item.getName()).isEqualTo("item1");
                assertThat(item.getPrice()).isEqualTo(19.99);
                return true;
            }).verifyComplete();
    }
}
