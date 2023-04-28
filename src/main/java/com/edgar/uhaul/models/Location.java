package com.edgar.uhaul.models;

import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity {
	
	

	@NotNull
	@Column(name = "name")
	private String locationName;

	@NotNull
	@Column(name = "street_number")
	private String locationStreetNumber;

	@NotNull
	@Column(name = "street_name")
	private String locationStreetName;

	@NotNull
	@Column(name = "city")
	private String locationStreetCity;

	@NotNull
	@Column(name = "state")
	private String locationStreetState;

	@NotNull
	@Column(name = "zipcode")
	private String locationStreetZipCode;

	@OneToMany(cascade = CascadeType.ALL)
	@Nullable
	private Set<Truck> trucks;

	@OneToMany(cascade = CascadeType.ALL)
	@Nullable
	private Set<Storage> storage;

	/* some locations don't have storage units... */
	private Boolean hasStorageUnits;

}
