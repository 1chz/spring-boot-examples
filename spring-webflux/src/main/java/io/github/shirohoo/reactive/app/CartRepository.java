package io.github.shirohoo.reactive.app;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
