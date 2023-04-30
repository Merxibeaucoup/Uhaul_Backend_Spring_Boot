package com.edgar.uhaul.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.LocationAlreadyExistsException;
import com.edgar.uhaul.exceptions.LocationDoesntExistException;
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
	
	/******************************************* Have Storage Units **********************************************************/
	
	/* get all locations within that zipCode that have storage units */
	public List<Location> getAllWithStorageByZipCode(String zipcode){
		if(isExistsByZipCode(zipcode)) {
			List<Location> location = locationRepository.findAll()
					.stream()
					.filter(l ->l.getLocationStreetZipCode() != null &&					
					l.getLocationStreetZipCode().equals(zipcode)
					&& l.getHasStorageUnits().equals(true))
					.collect(Collectors.toList());			
			
			return location;
		}
		else  
			throw new LocationDoesntExistException("location with zipcode :: "+ zipcode + " doesnt exist or doesnt have storage units");
	}
	
	
	/* get all locations within that city that have storage units */
	public List<Location> getAllWithStorageByCity(String city){
		
		if(isExistsByLocationCity(city)) {
			List<Location> location = locationRepository.findAll()
					.stream()
					.filter(l -> 
					l.getLocationStreetCity() != null
					&& l.getLocationStreetCity().equals(city)
					&& l.getHasStorageUnits() == true
							).collect(Collectors.toList());
			
			return location;
		}
		else  
			throw new LocationDoesntExistException("location with zipcode :: "+ city + " doesnt exist or doesnt have storage units");
	}
	
	
	/* get all locations within that state that have storage units */
	public List<Location> getAllWithStorageByState(String state){
		
		if(isExistsByLocationState(state)) {
			List<Location> location = locationRepository.findAll()
					.stream()
					.filter(l -> 
					l.getLocationStreetCity() != null
					&& l.getLocationStreetState().equals(state)
					&& l.getHasStorageUnits() == true
							).collect(Collectors.toList());
			
			return location;
		}
		else  
			throw new LocationDoesntExistException("location with zipcode :: "+ state + " doesnt exist or doesnt have storage units");
	}
	
	
	
	/* checks */
	private boolean isExistsByName(String name) {
		return locationRepository.existsByLocationName(name) ? true : false;
	}
	
	private boolean isExistsByZipCode(String zipcode) {
		return locationRepository.existsByLocationStreetZipCode(zipcode) ? true : false;
	}
	
	private boolean isExistsByLocationCity(String city) {
		return locationRepository.existsByLocationStreetCity(city) ? true : false;
	}
	
	private boolean isExistsByLocationState(String state) {
		return locationRepository.existsByLocationStreetState(state) ? true : false ;
	}

}
