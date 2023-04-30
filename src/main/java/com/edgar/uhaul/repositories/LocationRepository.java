package com.edgar.uhaul.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
	Optional<Location> findByLocationName(String locationName);	
	boolean existsByLocationName(String name);
	
	List<Location> findByLocationStreetZipCode(String locationStreetZipCode);
	boolean existsByLocationStreetZipCode(String zipcode);
	
	List<Location> findByLocationStreetCity(String locationStreetCity);
	boolean existsByLocationStreetCity(String city);
	
	List<Location> findByLocationStreetState(String locationStreetState);
	boolean existsByLocationStreetState(String state);

}
