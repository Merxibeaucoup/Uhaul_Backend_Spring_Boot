package com.edgar.uhaul.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.edgar.uhaul.models.enums.TruckType;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "trucks")
public class Truck extends BaseEntity {
	
	@NotNull
	@Positive
	private Integer truckSize;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TruckType truckType;
	
	
	@Nullable
	private String truckImageUrl;
	
	@Column(columnDefinition = "TEXT", nullable = false )
	private String truckDescription;
	
	@Nullable
	private Set<String> features ;
	
	@PositiveOrZero
	@NotNull
	private BigDecimal startPrice;
	
	@PositiveOrZero
	@NotNull
	private BigDecimal pricePerMile;
	
	@NotNull
	@PositiveOrZero
	private Integer quantity;
	
	@Nullable
	private Boolean isBookedOut;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Location> location;
	
	

}
