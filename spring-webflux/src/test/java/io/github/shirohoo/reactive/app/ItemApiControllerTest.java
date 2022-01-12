package io.github.shirohoo.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@AutoConfigureRestDocs
@WebFluxTest(ItemApiController.class)
class ItemApiControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ItemRepository itemRepository;

    @Test
    void findAll() {
        given(itemRepository.findAll())
                .willReturn(Flux.just(
                        Item.of("Alf alarm clock", 19.99),
                        Item.of("Smurf TV tray", 24.99),
                        Item.of("Wireless charging station", 25.99),
                        Item.of("iPhone lightning cable", 39.99),
                        Item.of("Double dog leash", 3.07),
                        Item.of("Pet interactive sound Toy", 2.29)
                ));

        webTestClient.get().uri("/api/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findAll", preprocessResponse(prettyPrint())));
    }

    @Test
    void postAddItem() {
        given(itemRepository.save(any()))
                .willReturn(Mono.just(Item.of("Alf alarm clock", 19.99)));

        webTestClient.post().uri("/api/items")
                .bodyValue(Item.of("Alf alarm clock", 19.99))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(document("addItem", preprocessResponse(prettyPrint())));
    }
}
