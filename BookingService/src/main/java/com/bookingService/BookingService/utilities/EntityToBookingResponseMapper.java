package com.bookingService.BookingService.utilities;

import com.bookingService.BookingService.entity.Booking;
import com.commonUtilities.CommonUtilities.responses.BookingResponse;

public class EntityToBookingResponseMapper {

    public static BookingResponse map(Booking booking) {
        return new BookingResponse(booking.getBooking_id(), "reserved");
    }
}
