package com.edgar.uhaul.requests;

import java.time.LocalDate;
import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TruckOrderRequest {
	
	@NotNull
	private LocalDate pickUpDate;
	
	@NotNull
	private String pickUpLocation;
	
	@Nullable
	private String dropOffLocation;
	
	@NotNull
	private String truckName;
	
	@Nullable
	private Set<String> packingSupplies;
	
	@Nullable
	private String storageName;

}
