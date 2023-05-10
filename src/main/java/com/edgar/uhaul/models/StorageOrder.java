package com.edgar.uhaul.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.edgar.uhaul.models.enums.OrderStatus;
import com.edgar.uhaul.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "storage_orders")
public class StorageOrder  extends BaseEntity {

	@OneToOne
	@NotNull
	private Location storageLocation;
	
	@OneToOne
	@NotNull
	private Storage storageName;
	
	@NotNull
	private LocalDate moveInDate;
	
	@OneToOne
	@NotNull
	private StorageInsurance propertyInsurance;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@OneToOne
	@JsonIgnore
	private User client;
	
	private BigDecimal totalDueToday;
}
