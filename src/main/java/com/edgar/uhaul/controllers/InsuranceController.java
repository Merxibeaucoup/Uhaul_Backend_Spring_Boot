package com.edgar.uhaul.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.StorageInsurance;
import com.edgar.uhaul.services.StorageInsuranceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vi/insurance")
@RequiredArgsConstructor
public class InsuranceController {
	
	private final StorageInsuranceService storageInsuranceService;
	
	
	/* use this maybe "/admin/storage/new" */
	@PostMapping("/storage/new")
	public ResponseEntity<StorageInsurance> addNewStorageInsurance(@RequestBody StorageInsurance storageInsurance){
		return ResponseEntity.ok(storageInsuranceService.newStorageInsurance(storageInsurance));
	}
	
	@GetMapping("/storage/all")
	public ResponseEntity<List<StorageInsurance>> allStorageInsurance(){
		return ResponseEntity.ok(storageInsuranceService.allStorageIns());
	}

}
