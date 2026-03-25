package com.seatInventory.SeatInventory.listner;

import com.commonUtilities.CommonUtilities.events.BookingCreatedEvent;
import com.seatInventory.SeatInventory.service.SeatInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.commonUtilities.CommonUtilities.commons.kafkaConfigProperties.MOVIE_BOOKING_EVENTS_TOPIC;
import static com.commonUtilities.CommonUtilities.commons.kafkaConfigProperties.SEAT_EVENT_GROUP;

@Component
@Slf4j
public class SeatInventoryListner {
    private final SeatInventoryService seatInventoryService;
    public  SeatInventoryListner(SeatInventoryService seatInventoryService) {
        this.seatInventoryService = seatInventoryService;
    }

    @KafkaListener(topics=MOVIE_BOOKING_EVENTS_TOPIC, groupId = SEAT_EVENT_GROUP)
    public void listenBookingEvent(BookingCreatedEvent bookingCreatedEvent) {
        log.info("Consuming Booking created event for bookingId{}",bookingCreatedEvent.bookingId());
        seatInventoryService.handleBooking(bookingCreatedEvent);
    }
}
