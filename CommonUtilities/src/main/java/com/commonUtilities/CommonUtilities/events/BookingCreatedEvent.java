package com.commonUtilities.CommonUtilities.events;

import java.time.Instant;
import java.util.List;

public record BookingCreatedEvent(String bookingId, String showId, String userId, List<String> seatIds, long amount) {
}
