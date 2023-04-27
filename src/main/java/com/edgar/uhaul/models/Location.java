package com.edgar.uhaul.models;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity {
	
	@NotNull
	private String locationName;
	
	@NotNull
	private String locationStreetNumber;
	
	@NotNull
	private String locationStreetName;
	
	@NotNull
	private String locationStreetCity;
	
	@NotNull
	private String locationStreetState;
	
	@NotNull
	private String locationStreetZipCode;
	
	@NotNull
	private String locationOpenDaysStart;
	
	@NotNull
	private String locationOpenDaysEnd;
	
	@NotNull
	private LocalTime locationOpenTime;
	
	@NotNull
	private LocalTime locationCloseTime;
	

}
