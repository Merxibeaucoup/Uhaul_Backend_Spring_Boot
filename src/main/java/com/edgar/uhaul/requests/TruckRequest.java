package com.edgar.uhaul.requests;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TruckRequest {
	@NotNull
	private String pickUplocation;
	
	@Nullable
	private String dropOffLocation;
	
	@NotNull
	private LocalDate pickUpDate;

}
