package com.seatInventory.SeatInventory.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class kafkaConfig {

    @Bean
    public NewTopic createNewTopic() {
        return new NewTopic("SeatInventoryTopic", 3, (short) 1);
    }
}
