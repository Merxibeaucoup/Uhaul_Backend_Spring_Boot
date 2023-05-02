package com.edgar.uhaul.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.Truck;
import com.edgar.uhaul.services.TruckService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/trucks")
@RequiredArgsConstructor
public class TruckController {
	
	private final TruckService truckService;
	
	
	@PostMapping("/new")
	public ResponseEntity<Truck> addNewTruck(@RequestBody Truck truck){
		return ResponseEntity.ok(truckService.newTruck(truck));
	}
	
	@GetMapping("/zipcode")
	public ResponseEntity<Set<Truck>> getAllTrucksAtZip(@RequestParam String zipcode){
		return ResponseEntity.ok(truckService.AllTrucksInZipcode(zipcode));
	}
	
	
	

}
