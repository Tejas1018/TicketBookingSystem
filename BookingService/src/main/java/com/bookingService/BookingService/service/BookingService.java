package com.bookingService.BookingService.service;


import com.bookingService.BookingService.entity.Booking;
import com.bookingService.BookingService.messaging.BookingEventProducer;
import com.bookingService.BookingService.repository.BookingRepository;
import com.bookingService.BookingService.utilities.BookingRequestToEntityMapper;
import com.bookingService.BookingService.utilities.EntityToBookingResponseMapper;
import com.commonUtilities.CommonUtilities.events.BookingCreatedEvent;
import com.commonUtilities.CommonUtilities.request.BookingRequest;
import com.commonUtilities.CommonUtilities.responses.BookingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingEventProducer bookingEventProducer;

    public BookingService(BookingRepository bookingRepository, BookingEventProducer bookingEventProducer) {
        this.bookingRepository = bookingRepository;
        this.bookingEventProducer = bookingEventProducer;
    }

    public BookingResponse bookSeats(BookingRequest request) {
        log.info("Booking seats from user{} for show {}",request.userId(),request.showId());

        // Mapping the request to entity
        var bookingRequestEntity = BookingRequestToEntityMapper.map(request);

        //persist reservation into DB
        var savedReservation = bookingRepository.save(bookingRequestEntity);

        //create booking created event
        var bookingCreatedEvent = buildBookingCreatedEvent(savedReservation);
        bookingEventProducer.bookingPublishEvent(bookingCreatedEvent);

        var response = EntityToBookingResponseMapper.map(savedReservation);

        return response;
    }

    private BookingCreatedEvent buildBookingCreatedEvent(Booking savedReservation) {
        return new BookingCreatedEvent(savedReservation.getBookingId(),savedReservation.getShowId(),savedReservation.getUserId(),savedReservation.getSeatIds(),savedReservation.getAmount());
    }
}
