package com.edgar.uhaul.services.cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.models.PackingSupply;
import com.edgar.uhaul.models.Storage;
import com.edgar.uhaul.models.Truck;
import com.edgar.uhaul.models.TruckOrder;
import com.edgar.uhaul.repositories.LocationRepository;
import com.edgar.uhaul.repositories.StorageRepository;
import com.edgar.uhaul.repositories.TruckOrderRepository;
import com.edgar.uhaul.repositories.TruckRepository;
import com.edgar.uhaul.repositories.packingSupplyRepository;
import com.edgar.uhaul.requests.TruckOrderRequest;

@Service
public class TruckOrderService {

	@Autowired
	private TruckOrderRepository truckOrderRepository;
	@Autowired
	private TruckRepository truckRepository;
	@Autowired
	private StorageRepository storageRepository;
	@Autowired
	private packingSupplyRepository packingSupplyRepository;
	@Autowired
	private LocationRepository locationRepository;

	
	
	BigDecimal  packSuppliesSum = new BigDecimal("");
	
	public TruckOrder makeTruckOrderCart(TruckOrderRequest truckOrderRequest) {			
		TruckOrder truckOrder = new TruckOrder();	
				
		if(!truckOrderRequest.getPackingSupplies().isEmpty()) {			
			truckOrderRequest.getPackingSupplies().forEach(e -> {
				
				List<PackingSupply> supplies = packingSupplyRepository.findAll()
						.stream()
						.filter(t -> t.getSupplyName() != null && t.getSupplyName().equals(e) 
						).collect(Collectors.toList());
				
				supplies.forEach(t -> truckOrder.getPackingSupplies().add(t));
				
				packSuppliesSum = supplies.stream()
					    .map(PackingSupply::getSupplyPrice)
					    .reduce(BigDecimal::add)
					    .get();
			});	
		}
		
		if(!truckOrderRequest.getStorageName().equals(null)) {
			Storage storage = storageRepository.findByStorageName(truckOrderRequest.getStorageName()).get();
			truckOrder.setStorage(storage);
		}
		
		Location location_pickUp = locationRepository.findByLocationName(truckOrderRequest.getPickUpLocation()).get();
		truckOrder.setPickUpLocation(location_pickUp);
		
		Location location_dropOff = locationRepository.findByLocationName(truckOrderRequest.getDropOffLocation()).get();
		truckOrder.setPickUpLocation(location_dropOff);
		
		Truck truck = truckRepository.findByTruckName(truckOrderRequest.getTruckName()).get();
		truckOrder.setTruck(truck);
		
		truckOrder.setPickUpDate(truckOrderRequest.getPickUpDate());
			
		
		truckOrder.setTotalPrice(truckOrder.getStorage().getMonthlyFee()
				.add(truckOrder.getTruck().getStartPrice().add(packSuppliesSum)
						));
			
		return truckOrderRepository.save(truckOrder);

	}

}
