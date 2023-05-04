package com.edgar.uhaul.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "packing_supplies")
public class PackingSupply extends BaseEntity{
	
	@NotNull
	private String supplyName;
	
	@NotNull
	private BigDecimal supplyPrice;
	
	@Column(columnDefinition = "TEXT")
	@NotNull
	private String supplyDescription;

}
