package com.edgar.uhaul.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.LocationAlreadyExistsException;
import com.edgar.uhaul.exceptions.LocationDoesntExistException;
import com.edgar.uhaul.exceptions.TruckDoesntExistException;
import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.models.Truck;
import com.edgar.uhaul.repositories.LocationRepository;
import com.edgar.uhaul.repositories.TruckRepository;
import com.edgar.uhaul.requests.StorageRequest;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private TruckRepository truckRepository;
	
	
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
	
	
	
	
	public Set<Location> getAllStorageShopsAtTargetLocation(StorageRequest storageRequest){		
		Set<Location> location = new HashSet<>();
		List<Location> location_at = locationRepository.findAll()
				.stream()
				.filter(l -> 
				l.getLocationStreetCity() != null && l.getLocationStreetCity().equals(storageRequest.getLocationRequest())
				|| l.getLocationStreetState() != null && l.getLocationStreetState().equals(storageRequest.getLocationRequest())
				|| l.getLocationStreetZipCode() != null && l.getLocationStreetZipCode().equals(storageRequest.getLocationRequest())
				&& l.getHasStorageUnits().equals(true)
				).collect(Collectors.toList());
		
		if(location_at.size() >0) {
			location_at.stream()
			.forEach(e -> location.add(e));
			return location;
		}
		else 
			throw new LocationDoesntExistException("location :: "+ storageRequest.getLocationRequest()+ " doesnt exist");
			
		// sort with size type and storageType in frontEnd
	}
	

	
	/******************************************* Have Trucks  **********************************************************/
	 
	/* get All Locations With Trucks With locationType  -> works */
	public Set<Location> getAllLocationsWithTrucksAtLocationCityStateOrZip(String truckName, String locationRequest){	
		
		Set<Location> locations = new HashSet<>();			
		List<Truck> trucks = truckRepository.findAll()
				.stream()
				.filter(t -> t.getLocation() !=null
				&& t.getLocation().getLocationStreetZipCode()!=null
				&& t.getLocation().getLocationStreetZipCode().equals(locationRequest)
				|| t.getLocation().getLocationStreetCity().equals(locationRequest)
				|| t.getLocation().getLocationStreetState().equals(locationRequest)
				&& t.getTruckName().equals(truckName)
						).collect(Collectors.toList());
		
		if(trucks.size() > 0) {
			trucks.stream()
			.forEach(l -> locations.add(l.getLocation()));
			return locations;
		}
		else 
			throw new TruckDoesntExistException("truck :: "+ truckName +" doesnt exist at :: "+ locationRequest);
		
		
		
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
