package com.edgar.uhaul.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
	
	Optional<Storage> findByStorageName(String storageName);
	boolean existsByStorageName(String storageName);

}
