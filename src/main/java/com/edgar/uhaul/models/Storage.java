package com.edgar.uhaul.models;

import java.math.BigDecimal;
import java.util.Set;

import com.edgar.uhaul.models.enums.StorageSizeType;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "storage")
public class Storage extends BaseEntity {
	
	
	
	@NotNull
	private String storageName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StorageSizeType storageSize;

	@Nullable
	private String storageImageUrl;

	@NotNull
	private String storageDimension;

	private Set<String> features;

	@NotNull
	private BigDecimal monthlyFee;

	@NotNull
	@PositiveOrZero
	private Integer quantityAtLocation;

	
	private Boolean isBookedOut;

	private String locationAt;

}
