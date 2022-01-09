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
        };
    }
}
