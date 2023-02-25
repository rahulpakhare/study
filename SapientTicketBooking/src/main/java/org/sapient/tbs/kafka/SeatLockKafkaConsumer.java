package org.sapient.tbs.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.sapient.tbs.dto.SeatBooking;
import org.sapient.tbs.service.Impl.HandleSeatLockRequestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class SeatLockKafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SeatLockKafkaConsumer.class);

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${seat.lock.kafka.topic.group.id}")
    private String seatLockGroupId;

    @Value(value = "${seat.lock.kafka.topic}")
    private String seatLockTopic;

    @Autowired
    private HandleSeatLockRequestServiceImpl seatLockRequestService;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                seatLockGroupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "org.sapient.tbs.dto.SeatBooking");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "${seat.lock.kafka.topic}", groupId = "${seat.lock.kafka.topic.group.id}")
    public void listenSeatLockTopic(SeatBooking seatBooking) {
        if (seatLockRequestService.isSeatsAvailable(seatBooking)) {
            seatLockRequestService.createAsyncRequestToReserveSeats(seatBooking);
        } else {
            logger.info("Sorry, Seats are not available");
            seatLockRequestService.updateSeatsNotAvailable();
        }
    }
}