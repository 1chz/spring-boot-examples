package io.github.shirohoo.reactive.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
class MongoDBLoader {
    @Bean
    CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
            mongo.save(Item.of("Alf alarm clock", 19.99));
            mongo.save(Item.of("Smurf TV tray", 24.99));
            mongo.save(Item.of("Wireless charging station", 25.99));
            mongo.save(Item.of("iPhone lightning cable", 39.99));
            mongo.save(Item.of("Double dog leash", 3.07));
            mongo.save(Item.of("Pet interactive sound Toy", 2.29));
        };
    }
}
