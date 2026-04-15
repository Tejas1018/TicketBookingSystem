package com.bookingService.BookingService.entity;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String booking_id;
    private String showId;
    private long amount;
    private SeatStatus status;
    private String userId;
    private List<String> seatIds;
    private LocalTime startTime;
}
