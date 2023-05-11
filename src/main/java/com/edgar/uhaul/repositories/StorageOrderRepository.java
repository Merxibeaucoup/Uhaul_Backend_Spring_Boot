package com.edgar.uhaul.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.StorageOrder;

@Repository
public interface StorageOrderRepository extends JpaRepository<StorageOrder, Long> {

}
