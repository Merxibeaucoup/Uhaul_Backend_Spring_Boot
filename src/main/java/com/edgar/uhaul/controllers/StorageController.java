package com.edgar.uhaul.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.Storage;
import com.edgar.uhaul.services.StorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/storage")
@RequiredArgsConstructor
public class StorageController {
	
	private final StorageService storageService;
	
	/* new storage */
	@PostMapping("/new")
	public ResponseEntity<Storage> newStorage(@RequestBody Storage storage){
		return ResponseEntity.ok(storageService.newStorageUnit(storage));
	}
	
	@GetMapping("/allUnitsAtLocation")
	public ResponseEntity<Set<Storage>> findAllStorageUnits(@RequestParam String locationName){
		return ResponseEntity.ok(storageService.findStorageUnitsAtLocation(locationName));
		
	}

}
