package com.edgar.uhaul.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
	
	List<Storage> findByStorageName(String storageName);
	boolean existsByStorageName(String storageName);
	
	List<Storage> findByLocationAt(String locationAt);

}
