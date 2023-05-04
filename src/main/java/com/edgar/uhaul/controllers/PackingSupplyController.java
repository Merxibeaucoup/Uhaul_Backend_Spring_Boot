package com.edgar.uhaul.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.PackingSupply;
import com.edgar.uhaul.services.PackingSupplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/packingSupplies")
@RequiredArgsConstructor
public class PackingSupplyController {
	
	private final PackingSupplyService packingSupplyService;
	
	
	
	@PostMapping("/new")
	public ResponseEntity<PackingSupply> addNewPackingSupply(@RequestBody PackingSupply packingSupply){
		return ResponseEntity.ok(packingSupplyService.addNewPackingSupply(packingSupply));
	}
	
	@GetMapping("/supply-name/{supplyName}")
	public ResponseEntity<PackingSupply> getSupplyByName(@PathVariable String supplyName){
		return ResponseEntity.ok(packingSupplyService.getOneBySupplyName(supplyName));
 
	}
	
	@GetMapping("/supply-id/{id}")
	public ResponseEntity<PackingSupply> getSupplyByName(@PathVariable Long id){
		return ResponseEntity.ok(packingSupplyService.getOneBySupplyId(id));
 
	}
	
    @DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	    packingSupplyService.deleteOneByid(id);
		return new ResponseEntity<>(HttpStatus.OK);
	          
	}

}
