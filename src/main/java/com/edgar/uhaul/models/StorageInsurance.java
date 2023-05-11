package com.edgar.uhaul.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stored_property_insurance")
public class StorageInsurance extends BaseEntity {
	
	@NotNull
	private String name;
	
	@PositiveOrZero
	@NotNull
	private BigDecimal price;
	
	@Column(columnDefinition = "TEXT", nullable = true)
	private String description;
	
	

}
