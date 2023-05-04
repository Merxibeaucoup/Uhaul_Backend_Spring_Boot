package com.edgar.uhaul.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.PackingSupply;
import com.edgar.uhaul.repositories.packingSupplyRepository;
import com.edgar.uhaul.services.PackingSupplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/packingSupplies")
@RequiredArgsConstructor
public class PackingSupplyController {
	
	private final PackingSupplyService packingSupplyService;
	
	private final packingSupplyRepository packingSupplyRepository;
	
	@PostMapping("/new")
	public ResponseEntity<PackingSupply> addNewPackingSupply(@RequestBody PackingSupply packingSupply){
		return ResponseEntity.ok(packingSupplyService.addNewPackingSupply(packingSupply));
	}

}
