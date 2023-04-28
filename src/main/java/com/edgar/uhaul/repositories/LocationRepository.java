package com.edgar.uhaul.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
	Optional<Location> findByLocationName(String locationName);	
	boolean existsByLocationName(String name);

}
