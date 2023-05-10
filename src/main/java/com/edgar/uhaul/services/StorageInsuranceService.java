package com.edgar.uhaul.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.StorageInsuranceAlreadyExistsException;
import com.edgar.uhaul.models.StorageInsurance;
import com.edgar.uhaul.repositories.StorageInsuranceRepository;

@Service
public class StorageInsuranceService {
	
	@Autowired
	private StorageInsuranceRepository storageInsuranceRepository;
	
	/* new storage insurance */
	public StorageInsurance newStorageInsurance(StorageInsurance insurance) {
		if(!isExistsByName(insurance.getName())) {
			return storageInsuranceRepository.save(insurance);
		}
		else
			throw new StorageInsuranceAlreadyExistsException("insurance with name :: "+insurance.getName()+ " already exists ");
	}
	
	public List<StorageInsurance> allStorageIns(){
		return storageInsuranceRepository.findAll();
	}
	
	
	
	/* checks */
	private boolean isExistsByName(String name) {
		return storageInsuranceRepository.existsByName(name) ? true : false ;
	}

}
