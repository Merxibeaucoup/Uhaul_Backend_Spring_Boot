package com.edgar.uhaul.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StorageResponse {
	
	
	private String locationName;

	
	private String locationStreetNumber;

	
	private String locationStreetName;

	
	private String locationStreetCity;

	
	private String locationStreetState;

	
	private String locationStreetZipCode;
	

}
