package com.edgar.uhaul.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.uhaul.models.TruckOrder;
import com.edgar.uhaul.requests.TruckOrderRequest;
import com.edgar.uhaul.security.user.User;
import com.edgar.uhaul.services.cart.TruckOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartOrderController {

	private final TruckOrderService truckOrderService;

	@PostMapping("/new")
	public ResponseEntity<TruckOrder> newTruckOrder(@RequestBody TruckOrderRequest truckOrderRequest, @AuthenticationPrincipal User user) {
		return ResponseEntity.ok(truckOrderService.newTruckOrderCart(truckOrderRequest, user));
	}

}
