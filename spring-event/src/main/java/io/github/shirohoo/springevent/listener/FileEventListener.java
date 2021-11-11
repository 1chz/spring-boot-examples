package io.github.shirohoo.springevent.listener;

import io.github.shirohoo.springevent.model.event.FileEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileEventListener {

    @EventListener
    public void receive(final FileEvent fileEvent) {
        log.info("file event receive: {}", fileEvent);
    }

}
