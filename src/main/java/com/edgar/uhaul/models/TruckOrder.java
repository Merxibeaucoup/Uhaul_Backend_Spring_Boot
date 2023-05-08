package com.edgar.uhaul.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.edgar.uhaul.models.enums.OrderStatus;
import com.edgar.uhaul.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
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
@Table(name = "pickup_truck_order")
public class TruckOrder extends BaseEntity {

	@NotNull
	private LocalDate pickUpDate;
	
	@NotNull
	@OneToOne
	private Location pickUpLocation;
	
	@Nullable
	@OneToOne
	private Location dropOffLocation;

	@NotNull
	@OneToOne
	private Truck truck;

	@Nullable
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<PackingSupply> packingSupplies;

	@OneToOne
	@Nullable
	private Storage storage;

	private BigDecimal totalPriceDueToday;
	
	private BigDecimal overallTotal;
	
	@OneToOne
	@JsonIgnore
	private User client;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

}
