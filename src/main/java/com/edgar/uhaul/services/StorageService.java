package com.edgar.uhaul.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.LocationDoesntExistException;
import com.edgar.uhaul.exceptions.StorageAlreadyExistsException;
import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.models.Storage;
import com.edgar.uhaul.models.enums.StorageSizeType;
import com.edgar.uhaul.repositories.LocationRepository;
import com.edgar.uhaul.repositories.StorageRepository;

@Service
public class StorageService {

	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private LocationRepository locationRepository;

	/* new storage unit */
	public Storage newStorageUnit(Storage storage) {

		Optional<Location> location_at = locationRepository.findByLocationName(storage.getLocationAt());
		Location location = location_at.orElseThrow(
				() -> new LocationDoesntExistException("location :: " + storage.getLocationAt() + " doesnt exist"));

		if ((isExistsByLocationName(storage.getLocationAt()) && (location.getHasStorageUnits() == true))) {
			
			 if(storage.getStorageSize() == StorageSizeType.SMALL) {
				storage.setMonthlyFee(new BigDecimal("109.95"));
				storage.setStorageName(StorageSizeType.SMALL.toString().toLowerCase()+ " | "+ storage.getStorageDimension());
			}
			 else if(storage.getStorageSize() == StorageSizeType.MEDIUM) {
				storage.setMonthlyFee(new BigDecimal("159.99"));
				storage.setStorageName(StorageSizeType.MEDIUM.toString().toLowerCase()+ " | "+ storage.getStorageDimension());

			}
			 else if(storage.getStorageSize() == StorageSizeType.LARGE) {
				storage.setMonthlyFee(new BigDecimal("239.99"));
				storage.setStorageName(StorageSizeType.LARGE.toString().toLowerCase()+ " | "+ storage.getStorageDimension());

			}
			 
			
			
			

			location.getStorage().add(storage);
			storageRepository.save(storage);
			locationRepository.save(location);
			return storage;
			

		} else {
			throw new StorageAlreadyExistsException("Storage already exists for that");
		}
			

	}
	
	
	
	

	/* checks */
//	private boolean isExistsByName(String name) {
//		return storageRepository.existsByStorageName(name) ? true : false;
//	}

	private boolean isExistsByLocationName(String name) {
		return locationRepository.existsByLocationName(name) ? true : false;
	}

}
