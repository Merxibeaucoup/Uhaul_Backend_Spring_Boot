package com.edgar.uhaul.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.Location;
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
	
	@GetMapping("/with-storage/zipcode")
	public ResponseEntity<List<Location>> getAllLocationsWithStorageByZipcode(@RequestParam String zipcode){
		return ResponseEntity.ok(locationService.getAllWithStorageByZipCode(zipcode));
	}
	
	@GetMapping("/with-storage/city")
	public ResponseEntity<List<Location>> getAllLocationsWithStorageByCity(@RequestParam String city){
		return ResponseEntity.ok(locationService.getAllWithStorageByCity(city));
	}
	
	@GetMapping("/with-storage/state")
	public ResponseEntity<List<Location>> getAllLocationsWithStorageByState(@RequestParam String state){
		return ResponseEntity.ok(locationService.getAllWithStorageByState(state));
	}

}
