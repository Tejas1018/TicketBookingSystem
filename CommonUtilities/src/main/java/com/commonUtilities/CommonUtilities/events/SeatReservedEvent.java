package com.commonUtilities.CommonUtilities.events;

public record SeatReservedEvent(String bookingId,boolean reserved,long amount) {
}
