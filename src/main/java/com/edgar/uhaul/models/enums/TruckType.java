package com.edgar.uhaul.models.enums;

import lombok.Getter;

@Getter
public enum TruckType {
	
	PICKUPTRUCK("Pickup Truck"),
	CARGOVAN("Cargo Van"),
	TRUCK("Truck");
	
	public final String label;

	TruckType(String label) {
		this.label = label;
	}

}
