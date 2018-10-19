package meet.generator.visit.adapters;


import lombok.extern.slf4j.Slf4j;
import meet.generator.visit.model.Disease;
import meet.generator.visit.model.Visit;
import meet.generator.visit.ports.VisitBindings;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import java.util.UUID;

import static meet.generator.visit.ports.VisitBindings.OUTPUT;

@Slf4j
@EnableBinding(VisitBindings.class)
public class VisitGenerator {

    @Bean
    @InboundChannelAdapter(channel = OUTPUT, poller = @Poller(fixedDelay = "1000"))
    public MessageSource<Visit> generate() {
        log.info("generate message");
        return () -> MessageBuilder
                .withPayload(Visit.builder()
                        .id(UUID.randomUUID().toString())
                        .diagnosedDisease(Disease.ADHD)
                        .build()
                )
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
    }


}