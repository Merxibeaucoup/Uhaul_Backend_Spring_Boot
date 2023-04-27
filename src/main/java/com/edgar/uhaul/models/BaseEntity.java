package com.edgar.uhaul.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@MappedSuperclass
@Data
@RequiredArgsConstructor
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime createdAtTimeStamp;
	
	@UpdateTimestamp
	private LocalDateTime updatedAtTimeStamp;

}
