package com.edgar.uhaul.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.PackingSupply;

@Repository
public interface packingSupplyRepository extends JpaRepository<PackingSupply, Long> {

	Optional<PackingSupply> findBySupplyName(String name);
	boolean existsBySupplyName(String name);

}
