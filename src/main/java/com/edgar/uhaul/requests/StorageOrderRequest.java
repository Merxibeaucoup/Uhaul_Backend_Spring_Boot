package com.edgar.uhaul.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StorageOrderRequest {

	@NotNull
	private String moveInLocation;

	@NotNull
	private String storageName;

	@NotNull
	private LocalDate moveInDate;

	@NotNull
	private String StorageInsurance;

}
