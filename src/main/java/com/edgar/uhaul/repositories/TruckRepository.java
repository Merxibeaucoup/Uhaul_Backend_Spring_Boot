package com.edgar.uhaul.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
	
	Optional<Truck> findByTruckName(String truckName);
	boolean existsByTruckName(String truckName);
	
	
	

}
