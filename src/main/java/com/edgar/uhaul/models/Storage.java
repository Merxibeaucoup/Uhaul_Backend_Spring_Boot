package com.edgar.uhaul.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.edgar.uhaul.models.enums.StorageSizeType;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "storage")
public class Storage extends BaseEntity {
	
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StorageSizeType storageSize;
	
	@NotNull
	private String storageDimension;
		
	private Set<String> features;
	
	@NotNull
	private BigDecimal monthlyFee;
	
	@NotNull
	@PositiveOrZero
	private Integer quantity;
	
	@Nullable
	private Boolean isBookedOut;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Location> location;

}
