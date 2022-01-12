package io.github.shirohoo.reactive.app;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hypermedia/items")
public class HypermediaItemApiController {
    private final ItemRepository itemRepository;

    @GetMapping("/{itemId}")
    Mono<EntityModel<Item>> findOne(@PathVariable String itemId) {
        HypermediaItemApiController controller = WebFluxLinkBuilder.methodOn(HypermediaItemApiController.class);
        Mono<Link> selfLink = Mono.just(linkTo(controller.findOne(itemId)).withSelfRel());
        return Mono.zip(itemRepository.findById(itemId), selfLink)
                .map(o -> EntityModel.of(o.getT1(), Links.of(o.getT2())));
    }
}
