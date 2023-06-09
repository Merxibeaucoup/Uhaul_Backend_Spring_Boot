package com.edgar.uhaul.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.requests.StorageRequest;
import com.edgar.uhaul.security.user.User;
import com.edgar.uhaul.services.LocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
public class LocationController {
	
	private final LocationService locationService;
	
	
	/* create new location */
	
	@PostMapping("/new")
	public ResponseEntity<Location> newLocation(@RequestBody Location location, @AuthenticationPrincipal User user){
		return ResponseEntity.ok(locationService.newLocation(location));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Location>> getAllLocations(){
		return ResponseEntity.ok(locationService.getAll());
	}
	
	/********************** Storage ****************************/
	

	
	@GetMapping("/with-storage/location")
	public ResponseEntity<Set<Location>> getAllLocationsWithStorageByZipcode(@RequestBody StorageRequest storageRequest){
		return ResponseEntity.ok(locationService.getAllStorageShopsAtTargetLocation(storageRequest));
	}
	

	
	/********************** Trucks ****************************/
	
	@GetMapping("/truckname/locationType")
	public ResponseEntity<Set<Location>> getAllLocationsWithTruckNameAndLocationType(@RequestParam String truckName, @RequestParam String locationRequest){
		return ResponseEntity.ok(locationService.getAllLocationsWithTrucksAtLocationCityStateOrZip(truckName, locationRequest));
	}
	


}
