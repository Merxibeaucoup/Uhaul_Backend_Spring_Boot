package com.edgar.uhaul.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.TruckOrder;

@Repository
public interface TruckOrderRepository extends JpaRepository<TruckOrder, Long> {

}
