package com.edgar.uhaul.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.LocationAlreadyExistsException;
import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.repositories.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	
	/* create new location */
	public Location newLocation(Location location) {
		
		if(!isExistsByName(location.getLocationName())) {
			return locationRepository.save(location);
		}
		else 
			throw new  LocationAlreadyExistsException(" location with name :: "+ location.getLocationName() + " already exists ");
		
	}
	
	/* get all locations */
	public List<Location> getAll(){
		return locationRepository.findAll();
	}
	
	
	
	/* checks */
	private boolean isExistsByName(String name) {
		return locationRepository.existsByLocationName(name) ? true : false;
	}

}
