package com.edgar.uhaul.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.StorageOrder;
import com.edgar.uhaul.models.TruckOrder;
import com.edgar.uhaul.requests.ReturnTruckRequest;
import com.edgar.uhaul.requests.StorageOrderRequest;
import com.edgar.uhaul.requests.TruckOrderRequest;
import com.edgar.uhaul.security.user.User;
import com.edgar.uhaul.services.cart.StorageOrderService;
import com.edgar.uhaul.services.cart.TruckOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartOrderController {

	private final TruckOrderService truckOrderService;
	
	private final StorageOrderService storageOrderService;
	
	
	/** truckOrder Services **/

	@PostMapping("/truck-order/new")
	public ResponseEntity<TruckOrder> newTruckOrder(@RequestBody TruckOrderRequest truckOrderRequest, @AuthenticationPrincipal User user) {
		return ResponseEntity.ok(truckOrderService.newTruckOrderCart(truckOrderRequest, user));
	}
	
	@GetMapping("/truck-order/all")
	public ResponseEntity<List<TruckOrder>> getAllTruckOrders(){
		return ResponseEntity.ok(truckOrderService.getAllTruckOrders());
	}
	
	@GetMapping("/truck-order/get/{id}")
	public ResponseEntity<TruckOrder> getTruckOrderById(@PathVariable Long id){
		return ResponseEntity.ok(truckOrderService.getTruckOrderById(id));
	}
	
	@PutMapping("/truck-order/cancel/{id}")
	public ResponseEntity<?> cancelTruckorder(@PathVariable Long id){
		truckOrderService.cancelTruckOrderReservation(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/truck-order/delete/{id}")
	public ResponseEntity<?> deleteTruckOrderbyId(@PathVariable Long id){
		 truckOrderService.deleteTruckOrderById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/truck-order/pick-up/{id}")
	public ResponseEntity<?> pickUpReservedTruck(@PathVariable Long id){
		truckOrderService.pickUpReservedTruck(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/truck-order/return/{id}")
	public ResponseEntity<?> returnRentedTruck(@PathVariable Long id, @RequestBody ReturnTruckRequest req){
		truckOrderService.returnRentedTruck(id, req);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	/** storageOrder Services **/
	
	@PostMapping("/storage-order/new")
	public ResponseEntity<StorageOrder> newStorageOrder(@RequestBody StorageOrderRequest storageOrderRequest, @AuthenticationPrincipal User user){
		return ResponseEntity.ok(storageOrderService.newStorageOrder(storageOrderRequest, user));
	}
	
	@GetMapping("/storage-order/all")
	public ResponseEntity<List<StorageOrder>> getAllStorageOrders(){
		return ResponseEntity.ok(storageOrderService.allStorageOrders());
	}
	
	@GetMapping("storage-order/get/{id}")
	public ResponseEntity<StorageOrder> getStorageOrderById(@PathVariable Long id){
		return ResponseEntity.ok(storageOrderService.getStorageOrderById(id));
	}

}
