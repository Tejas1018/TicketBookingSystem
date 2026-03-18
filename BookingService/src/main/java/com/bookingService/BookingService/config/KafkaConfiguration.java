package com.bookingService.BookingService.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic createBookingTopic() {
        return new NewTopic("Bookings", 3, (short) 1);
    }

}
