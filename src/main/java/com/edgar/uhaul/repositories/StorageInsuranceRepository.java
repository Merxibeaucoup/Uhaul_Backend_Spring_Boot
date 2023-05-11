package com.edgar.uhaul.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.StorageInsurance;

@Repository
public interface StorageInsuranceRepository extends JpaRepository<StorageInsurance, Long> {	
	
	Optional<StorageInsurance>findByName(String name);
	boolean existsByName(String name);
}
