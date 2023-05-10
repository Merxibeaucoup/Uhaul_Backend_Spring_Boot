package com.edgar.uhaul.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.StorageInsurance;
import com.edgar.uhaul.models.StorageOrder;

@Repository
public interface StorageInsuranceRepository extends JpaRepository<StorageInsurance, Long> {	
	
	Optional<StorageOrder>findByName(String name);
	boolean existsByName(String name);
}
