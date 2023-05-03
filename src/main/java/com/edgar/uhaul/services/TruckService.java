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
	
	/* get all trucks in zipcode  change this to --> all ... city , state, zipcode */	
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
	
	
	
	
	
	
	
	
	
	/* checks */
	
	private boolean isExistsByTruckName(String truckName) {
		return truckRepository.existsByTruckName(truckName) ? true : false ;
	}

}
