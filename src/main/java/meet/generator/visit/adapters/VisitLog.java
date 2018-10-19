package meet.generator.visit.adapters;

import lombok.extern.slf4j.Slf4j;
import meet.generator.visit.model.Visit;
import meet.generator.visit.ports.VisitBindings;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VisitLog {

    @StreamListener(VisitBindings.INPUT)
    public void log(@Payload Visit visit) {
        log.info("Received visit: {}", visit);
    }
}