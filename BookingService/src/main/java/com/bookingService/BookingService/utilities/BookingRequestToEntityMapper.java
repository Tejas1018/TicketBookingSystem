package com.bookingService.BookingService.utilities;

import com.bookingService.BookingService.entity.Booking;
import com.bookingService.BookingService.entity.SeatStatus;
import com.commonUtilities.CommonUtilities.request.BookingRequest;

import java.awt.print.Book;
import java.util.UUID;

public class BookingRequestToEntityMapper {

    public static Booking map(BookingRequest bookingRequest) {
        var reservationId = UUID.randomUUID().toString().split("-")[0];
        Booking booking = new Booking();
        booking.setBooking_id(booking.getBooking_id());
        booking.setShowId(booking.getShowId());
        booking.setUserId(booking.getUserId());
        booking.setSeatIds(booking.getSeatIds());
        booking.setStatus(SeatStatus.reserved);
        booking.setAmount(bookingRequest.amount());
        return booking;
    }
}
