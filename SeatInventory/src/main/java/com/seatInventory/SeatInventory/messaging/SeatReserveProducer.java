package com.seatInventory.SeatInventory.messaging;

import com.commonUtilities.CommonUtilities.events.SeatReservedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.commonUtilities.CommonUtilities.commons.kafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Component
@Slf4j
public class SeatReserveProducer {

    public KafkaTemplate<String, SeatReservedEvent> template;
    public SeatReserveProducer(KafkaTemplate<String, SeatReservedEvent> template) {
        this.template = template;
    }

    public void publishSeatReservedEvent(SeatReservedEvent reservedEvent){
        try{
            log.info("SeatReserveProducer::Publishing SeatReservedEvent for bookingId{}",reservedEvent.bookingId());
            template.send(SEAT_RESERVED_TOPIC,reservedEvent.bookingId(), reservedEvent);
        }
        catch (Exception e){
            log.error("ERROR :: While publishing SeatReservedEvent for bookingId{}",reservedEvent.bookingId());
        }
    }
}
