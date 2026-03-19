package com.paymentService.PaymentService.listner;


import com.commonUtilities.CommonUtilities.events.SeatReservedEvent;
import com.paymentService.PaymentService.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.commonUtilities.CommonUtilities.commons.kafkaConfigProperties.PAYMENT_EVENT_GROUP;
import static com.commonUtilities.CommonUtilities.commons.kafkaConfigProperties.SEAT_RESERVED_TOPIC;

@Service
@Slf4j
public class SeatReservedEventConsumer {
    private final PaymentService paymentService;
    public SeatReservedEventConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @KafkaListener(topics = SEAT_RESERVED_TOPIC,groupId = PAYMENT_EVENT_GROUP)
    public void consume(SeatReservedEvent seatReservedEvent) {
        try {
            log.info("Received SeatReservedEvent from Kafka topic: {}", seatReservedEvent);
            if (seatReservedEvent.reserved()) {
                paymentService.processEvent(seatReservedEvent);
            } else {
                log.info("Skipping Payment as Seat booking is failed for bookingId{}", seatReservedEvent.bookingId());
            }
        }
        catch (Exception e) {
            log.warn("Failed to consume SeatReservedEvent from Kafka topic: {}", seatReservedEvent, e);
        }
    }
}
