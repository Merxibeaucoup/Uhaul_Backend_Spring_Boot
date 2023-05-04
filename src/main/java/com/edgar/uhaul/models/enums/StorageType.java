package com.edgar.uhaul.models.enums;

import lombok.Getter;

@Getter
public enum StorageType {
	
	CLIMATECONTROL("Climate Control"),
	INDOOR("Indoor Storage"),
	OUTDOOR("Outdoor/ Drive Up"),
	WINE("Wine Storage"),
	ONLINE("Online Move-In/Rent Now"),
	VEHICLE("RV/Boat/Vehicle Storage")
	;
	
	
	
	public final String label;
	
	 StorageType( String label) {
		this.label = label;
	}

}
