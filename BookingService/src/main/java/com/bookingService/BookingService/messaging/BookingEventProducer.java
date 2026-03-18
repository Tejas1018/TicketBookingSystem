package com.bookingService.BookingService.messaging;


import com.commonUtilities.CommonUtilities.events.BookingCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import static com.commonUtilities.CommonUtilities.commons.kafkaConfigProperties.MOVIE_BOOKING_EVENTS_TOPIC;

@Component
@Slf4j
public class BookingEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookingEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void bookingPublishEvent(BookingCreatedEvent bookingEvent) {
        try{
            log.info("publishing booking event with id{}",bookingEvent.bookingId());

            kafkaTemplate.send(MOVIE_BOOKING_EVENTS_TOPIC,bookingEvent.bookingId(),bookingEvent);

        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
