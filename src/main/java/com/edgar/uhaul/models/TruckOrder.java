package com.edgar.uhaul.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "pickup_order")
public class TruckOrder extends BaseEntity {

	@NotNull
	private LocalDate pickUpDate;
	
	@NotNull
	private Location pickUpLocation;
	
	@Nullable
	private Location dropOffLocation;

	@NotNull
	@OneToOne
	private Truck truck;

	@Nullable
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PackingSupply> packingSupplies;

	@OneToOne
	@Nullable
	private Storage storage;

	private BigDecimal totalPrice;

}