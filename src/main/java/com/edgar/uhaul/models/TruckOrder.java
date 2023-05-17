package com.edgar.uhaul.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.edgar.uhaul.models.enums.OrderStatus;
import com.edgar.uhaul.security.user.User;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Location pickUpLocation;
	
	@Nullable
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Location dropOffLocation;

	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Truck truck;

	@Nullable
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<PackingSupply> packingSupplies;

	
	@Nullable
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Storage storage;

	private BigDecimal totalPriceDueToday;
	
	private BigDecimal overallTotal;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private User client;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private Boolean isCancelled;
	
	private Boolean isPickedUp;
	
	private Boolean isReturned;
	
	@Positive
	private Integer MileageUsed;

}
