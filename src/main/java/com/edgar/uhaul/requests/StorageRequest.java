package com.edgar.uhaul.requests;

import com.edgar.uhaul.models.enums.StorageSizeType;
import com.edgar.uhaul.models.enums.StorageType;

import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StorageRequest {
	
	@NotNull
	private String locationRequest;
	
	@Enumerated(EnumType.STRING)
	@Nullable
	private StorageSizeType storageSize;
	
	@Enumerated(EnumType.STRING)
	@Nullable
	private StorageType storageType;

}
