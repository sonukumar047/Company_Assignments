package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    Optional<ParkingLot> findByCarNumber(String carNumber);

    Optional<ParkingLot> findBySlotNumber(Integer slotNumber);
    
    @Query(value = "SELECT MIN(slotNumber) FROM ParkingLot p WHERE p.carNumber IS NULL")
    Integer findFirstAvailableSlot();
}
