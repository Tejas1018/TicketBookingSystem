package com.seatInventory.SeatInventory.service;


import com.commonUtilities.CommonUtilities.events.BookingCreatedEvent;
import com.commonUtilities.CommonUtilities.events.SeatReservedEvent;
import com.seatInventory.SeatInventory.entity.SeatInventory;
import com.seatInventory.SeatInventory.entity.SeatStatus;
import com.seatInventory.SeatInventory.messaging.SeatReserveProducer;
import com.seatInventory.SeatInventory.repository.SeatInventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeatInventoryService {
    private final SeatInventoryRepository seatInventoryRepository;
    private final SeatReserveProducer seatReserveProducer;

    public SeatInventoryService(SeatInventoryRepository seatInventoryRepository, SeatReserveProducer seatReserveProducer) {
        this.seatInventoryRepository = seatInventoryRepository;
        this.seatReserveProducer = seatReserveProducer;
    }

    public void handleBooking(BookingCreatedEvent event) {
        log.info("Processing Booking created event for bookingId{}",event.bookingId());

        //Fetch All Seats for booking
        List<SeatInventory> seats = seatInventoryRepository.findByShowIdAndSeatNumbersIn(event.bookingId(),event.seatIds());

        // check if all seats which need to be booked are available or not.
        boolean isAllSeatsAvailable = seats.stream().allMatch(s->s.getSeatStatus()== SeatStatus.AVALIABLE);
        // If Available Set Lock and bookingId
        if(isAllSeatsAvailable) {
            seats.forEach(seat -> {
                seat.setSeatStatus(SeatStatus.LOCKED);
                seat.setCurrentBookingId(event.bookingId());
            });
            // Persist all the seats to repository
            seatInventoryRepository.saveAll(seats);
            // Publish an event to kafka
            seatReserveProducer.publishSeatReservedEvent(new SeatReservedEvent(event.bookingId(),true,event.amount()));
        }

        else{
            log.warn("SeatInventoryService :: Booking failed for this bookingId{},since some seats are not avaialble",event.bookingId());
        }

    }
}
