package com.commonUtilities.CommonUtilities.request;

import java.time.Instant;
import java.util.List;

public record BookingRequest(String reservationId, String showId, String userId,List<String> seatIds, Long amount, Instant timestamp) {

}
