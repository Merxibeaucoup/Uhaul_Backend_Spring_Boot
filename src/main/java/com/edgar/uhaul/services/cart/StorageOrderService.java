package com.edgar.uhaul.services.cart;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.LocationDoesntExistException;
import com.edgar.uhaul.exceptions.StorageInsuranceDoesntExistException;
import com.edgar.uhaul.exceptions.StorageUnitsDoesntExistAtLocationException;
import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.models.Storage;
import com.edgar.uhaul.models.StorageInsurance;
import com.edgar.uhaul.models.StorageOrder;
import com.edgar.uhaul.models.enums.OrderStatus;
import com.edgar.uhaul.repositories.LocationRepository;
import com.edgar.uhaul.repositories.StorageInsuranceRepository;
import com.edgar.uhaul.repositories.StorageOrderRepository;
import com.edgar.uhaul.repositories.StorageRepository;
import com.edgar.uhaul.requests.StorageOrderRequest;
import com.edgar.uhaul.security.user.User;

@Service
public class StorageOrderService {

	@Autowired
	private StorageOrderRepository storageOrderRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private StorageRepository storageRepository;
	@Autowired
	private StorageInsuranceRepository storageInsuranceRepository;
	
	LocalDate today = LocalDate.now();
	BigDecimal totalDueToday = new BigDecimal("0.00");

	/* new storageOrder */
	public StorageOrder newStorageOrder(StorageOrderRequest orderRequest, User user) {

		StorageOrder storageOrder = new StorageOrder();	
		
		Location location = locationRepository.findByLocationName(orderRequest.getMoveInLocation())
				.orElseThrow(()-> new LocationDoesntExistException("location with name :: "+orderRequest.getMoveInLocation()+ " doesnt exist"));	
		storageOrder.setStorageLocation(location);
		
		Storage storage = storageRepository.findByStorageName(orderRequest.getStorageName())
				.orElseThrow(() -> new StorageUnitsDoesntExistAtLocationException("Storage unit doesnt exist"));
		storageOrder.setStorageName(storage);
		
		if(orderRequest.getMoveInDate().equals(today)) {
			storageOrder.setOrderStatus(OrderStatus.RENTED);
		}
		else if(orderRequest.getMoveInDate().isAfter(today)) {
			storageOrder.setOrderStatus(OrderStatus.RESERVED);
		}
		
		storageOrder.setMoveInDate(orderRequest.getMoveInDate());
		
		StorageInsurance insurance = storageInsuranceRepository.findByName(orderRequest.getStorageInsurance())
				.orElseThrow(()-> new StorageInsuranceDoesntExistException("Storage insurance with name :: "+orderRequest.getStorageInsurance()+ " doesnt exist"));
			
		storageOrder.setPropertyInsurance(insurance);	
		storageOrder.setClient(user);
		
		totalDueToday = storage.getMonthlyFee().add(insurance.getPrice());
		
		storageOrder.setTotalDueToday(totalDueToday);
		
		return storageOrderRepository.save(storageOrder);
	}
	
	
	/* all storage orders */
	public List<StorageOrder> allStorageOrders(){
		return storageOrderRepository.findAll();
	}

}
