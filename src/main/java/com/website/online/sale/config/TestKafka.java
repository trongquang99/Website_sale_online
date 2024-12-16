package com.website.online.sale.config;

import com.website.online.sale.dtos.kafka_event.UserEvent;
import com.website.online.sale.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestKafka {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TestKafka(@Qualifier(KafkaConfig.KAFKA_PRODUCER) KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = {"${kafka.topic.test}"},
            autoStartup = "true",
            containerFactory = KafkaConfig.KAFKA_CONSUMER,
            groupId = "car-service-test-event"
    )
    public void processKafka(String data) {
        try {
            log.info("processKafka data: {}", data);
            UserEvent event = JsonUtils.parse(data, UserEvent.class);
            String b = data;
            kafkaTemplate.send("send-kafka-success", event.getUsername(), JsonUtils.stringify(event));
        } catch (Exception ex) {
            log.error("Error processMessageV4Packages", ex);
        }
    }

}
