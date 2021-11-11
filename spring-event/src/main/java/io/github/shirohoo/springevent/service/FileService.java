package io.github.shirohoo.springevent.service;

import io.github.shirohoo.springevent.model.event.FileEvent;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final ApplicationEventPublisher publisher;

    public void fileUpload(final Map<String, Object> data) {
        try {
            log.info("file upload success.");
            publisher.publishEvent(FileEvent.complete(data));
        } catch (Exception e) {
            log.error("file upload fail.", e);
            publisher.publishEvent(FileEvent.error(data));
        }
    }

}
