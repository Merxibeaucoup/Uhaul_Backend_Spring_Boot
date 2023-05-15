package com.edgar.uhaul.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReturnTruckRequest {

	@NotNull
	private Integer mileageUsed;
}
