package com.seatInventory.SeatInventory.repository;

import com.seatInventory.SeatInventory.entity.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatInventoryRepository extends JpaRepository<SeatInventory, Long> {

    public List<SeatInventory> findByShowIdAndSeatNumbersIn(String showId, List<String> seatNumbers);
}
