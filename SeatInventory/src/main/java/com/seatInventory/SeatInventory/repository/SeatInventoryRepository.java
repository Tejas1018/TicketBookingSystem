package com.seatInventory.SeatInventory.repository;

import com.seatInventory.SeatInventory.entity.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SeatInventoryRepository extends JpaRepository<SeatInventory, Long> {

    List<SeatInventory> findByShowIdAndSeatNumbersIn(String showId, List<String> seatNumbers);
}
