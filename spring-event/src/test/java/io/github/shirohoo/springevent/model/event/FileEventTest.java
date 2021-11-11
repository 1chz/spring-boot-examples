package io.github.shirohoo.springevent.model.event;

import static org.assertj.core.api.Assertions.assertThat;
import io.github.shirohoo.springevent.model.event.FileEvent.EventType;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class FileEventTest {

    @Test
    void complete() {
        FileEvent event = FileEvent.complete(Collections.emptyMap());
        assertThat(event.getEventType()).isEqualTo(EventType.COMPLETE);
    }

    @Test
    void error() {
        FileEvent event = FileEvent.error(Collections.emptyMap());
        assertThat(event.getEventType()).isEqualTo(EventType.ERROR);
    }

}
