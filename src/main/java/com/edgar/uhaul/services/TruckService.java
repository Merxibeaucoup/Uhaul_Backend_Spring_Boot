package com.edgar.uhaul.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.TruckAlreadyExistsException;
import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.models.Truck;
import com.edgar.uhaul.repositories.LocationRepository;
import com.edgar.uhaul.repositories.TruckRepository;
import com.edgar.uhaul.requests.TruckRequest;

@Service
public class TruckService {
	
	@Autowired
	private TruckRepository truckRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	
	
	public Truck newTruck(Truck truck) {
		
		Optional<Location> location_at = locationRepository.findByLocationName(truck.getLocationAt());
		Location location = location_at.get();	
		
		if(!isExistsByTruckName(truck.getTruckName())) {
			
			truck.setTruckName(truck.getTruckSize()+"' "+ truck.getTruckType().label);
			location.getTrucks().add(truck);
			truckRepository.save(truck);
			truck.setLocation(location);
			locationRepository.save(location);
			 return truck;
		}
		else 
			throw new TruckAlreadyExistsException("Truck with name :: "+truck.getTruckName() +" already exists ");
		
	}
	
	/* get all trucks in zipcode  changed to below - */	
	public Set<Truck> AllTrucksInZipcode(String zipcode){	
		
		Set<Truck> trucks = new HashSet<>();
		List<Location> locations = locationRepository.findAll()
				.stream()
				.filter(l -> 
				l.getLocationStreetZipCode()!=null
				&& l.getLocationStreetZipCode().equals(zipcode)
						).collect(Collectors.toList());
		
		if(locations.size() > 0) {
			locations.stream()
			.forEach(t-> trucks.addAll(t.getTrucks()));
		}		
		return trucks;		
	}
	
	
	/* get all trucks at location*/
	public Set<Truck> getAllTrucksAtLocation(TruckRequest truckRequest){
		
		Set<Truck> trucks_at_location = new HashSet<>();
		
		List<Location> location  = locationRepository.findAll()
				.stream()
				.filter(l ->
				l.getLocationStreetCity()!=null	&& l.getLocationStreetCity().equals(truckRequest.getPickUplocation())
				|| l.getLocationStreetState()!=null	&& l.getLocationStreetState().equals(truckRequest.getPickUplocation())
				|| l.getLocationStreetZipCode()!=null && l.getLocationStreetZipCode().equals(truckRequest.getPickUplocation())
						).collect(Collectors.toList());
				
	
		if(location.size() > 0) {
			location
			.stream()
			.forEach(t -> trucks_at_location.addAll(t.getTrucks()));
		}
		
		
		if(truckRequest.getDropOffLocation() != null) 
		{
				truckRequest.setDropOffLocation(truckRequest.getPickUplocation());
		}
		else {
					truckRequest.setDropOffLocation(truckRequest.getDropOffLocation());			
		}	
			
		return  trucks_at_location;		
		// in the front end use a set and get only  unique truckNames
	}
	
	
	/* checks */
	
	private boolean isExistsByTruckName(String truckName) {
		return truckRepository.existsByTruckName(truckName) ? true : false ;
	}

}
