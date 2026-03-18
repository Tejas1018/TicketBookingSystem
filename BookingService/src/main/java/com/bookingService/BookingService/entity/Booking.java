package com.bookingService.BookingService.entity;


import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="Booking")
public class Booking {
    @Id
    private String bookingId;
    private String showId;
    private long amount;
    private SeatStatus status;
    private String userId;
    private List<String> seatIds;
    private LocalTime startTime;
}
