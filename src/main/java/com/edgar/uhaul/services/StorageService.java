package com.edgar.uhaul.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.LocationDoesntExistException;
import com.edgar.uhaul.exceptions.StorageAlreadyExists;
import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.models.Storage;
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

		if ((!isExistsByName(storage.getStorageName()))
				&& (isExistsByLocationName(storage.getLocationAt()) && (location.getHasStorageUnits() == true))) {

			location.getStorage().add(storage);
			locationRepository.save(location);
			return  storageRepository.save(storage);

		} else {
			throw new StorageAlreadyExists("Storage already exists for that");
		}
		
		

	}
	
	
	
	

	/* checks */
	private boolean isExistsByName(String name) {
		return storageRepository.existsByStorageName(name) ? true : false;
	}

	private boolean isExistsByLocationName(String name) {
		return locationRepository.existsByLocationName(name) ? true : false;
	}

}
