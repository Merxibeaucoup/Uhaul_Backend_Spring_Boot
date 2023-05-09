package com.edgar.uhaul.services.cart;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edgar.uhaul.exceptions.DateIsNotTodayOrAfterTodayException;
import com.edgar.uhaul.exceptions.TruckOrderCanNotBeCancelled;
import com.edgar.uhaul.exceptions.TruckOrderDoesntExistException;
import com.edgar.uhaul.models.Location;
import com.edgar.uhaul.models.PackingSupply;
import com.edgar.uhaul.models.Storage;
import com.edgar.uhaul.models.Truck;
import com.edgar.uhaul.models.TruckOrder;
import com.edgar.uhaul.models.enums.OrderStatus;
import com.edgar.uhaul.repositories.LocationRepository;
import com.edgar.uhaul.repositories.StorageRepository;
import com.edgar.uhaul.repositories.TruckOrderRepository;
import com.edgar.uhaul.repositories.TruckRepository;
import com.edgar.uhaul.repositories.packingSupplyRepository;
import com.edgar.uhaul.requests.TruckOrderRequest;
import com.edgar.uhaul.security.user.User;

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

	
	
	BigDecimal  packSuppliesSum = new BigDecimal("0.00");
	HashSet<PackingSupply> pSupplies = new HashSet<>();
	
	LocalDate today = LocalDate.now();
	
	
	/* new truck order */
	@Transactional
	public TruckOrder newTruckOrderCart(TruckOrderRequest truckOrderRequest, User currentUser) {			
		TruckOrder truckOrder = new TruckOrder();	
				
		if(!truckOrderRequest.getPackingSupplies().isEmpty()) {			
			truckOrderRequest.getPackingSupplies().forEach(e -> {
				
				List<PackingSupply> supplies = packingSupplyRepository.findAll()
						.stream()
						.filter(t -> t.getSupplyName() != null && t.getSupplyName().equals(e) 
						).collect(Collectors.toList());
								
				supplies.forEach(t -> pSupplies.add(t));								    
			});	
		}
		
		truckOrder.setPackingSupplies(pSupplies);
		
		packSuppliesSum = truckOrder.getPackingSupplies()
				.stream()
                .map(x -> x.getSupplyPrice()) // map
                .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal storageStartFee ;
		
		if(truckOrderRequest.getStorageName() != ("")) {
			Storage storage = storageRepository.findByStorageName(truckOrderRequest.getStorageName()).get();
			truckOrder.setStorage(storage);
			storageStartFee = new BigDecimal(truckOrder.getStorage().getMonthlyFee().doubleValue());
			storage.setQuantityAtLocation(storage.getQuantityAtLocation() - 1);
			storageRepository.save(storage);
		}
		else { 
			truckOrder.setStorage(null);
			storageStartFee = new BigDecimal("0.00");
			};	
		
		Location location_pickUp = locationRepository.findByLocationName(truckOrderRequest.getPickUpLocation()).get();
		truckOrder.setPickUpLocation(location_pickUp);
		
		
		if(truckOrderRequest.getDropOffLocation() == "") {
			truckOrder.setDropOffLocation(truckOrder.getPickUpLocation());
		}
		
		else {
			
			Location location_dropOff = locationRepository.findByLocationName(truckOrderRequest.getDropOffLocation()).get();
			truckOrder.setDropOffLocation(location_dropOff);
			
		}
				
		Truck truck = truckRepository.findByTruckName(truckOrderRequest.getTruckName()).get();
		truckOrder.setTruck(truck);
		truck.setQuantityAtLocation(truck.getQuantityAtLocation()-1);
		truckRepository.save(truck);
		
		truckOrder.setPickUpDate(truckOrderRequest.getPickUpDate());

		truckOrder.setTotalPriceDueToday(
				truckOrder.getTruck().getStartPrice().add(packSuppliesSum).add(storageStartFee)
						);
		
		truckOrder.setClient(currentUser);
		
		if(truckOrderRequest.getPickUpDate().isAfter(today)) {
			truckOrder.setOrderStatus(OrderStatus.RESERVED);
		}
		else if (truckOrderRequest.getPickUpDate().isEqual(today)) {
			truckOrder.setOrderStatus(OrderStatus.RENTED);		
			}
		else 
			throw new DateIsNotTodayOrAfterTodayException("date :: " + truckOrderRequest.getPickUpDate() + " is not today or after today ");
		
			
		return truckOrderRepository.save(truckOrder);

	}
	
	/* all truck orders */
	public List<TruckOrder> getAllTruckOrders(){
		return truckOrderRepository.findAll();
	}
	
	/* get truck order by id */
	public TruckOrder getTruckOrderById(Long id) {
		return truckOrderRepository.findById(id).orElse(null);
	}
	
	/* delete truck order by id */
	public void deleteTruckOrderById(Long id) {
		truckOrderRepository.deleteById(id);
	}
	
	/* cancel truckOrder reservation  -> create a scheduler to delete from db after 48 hours */
	public void cancelTruckOrderReservation(Long id) {
		TruckOrder truckOrder = truckOrderRepository.findById(id)
				.orElseThrow(()-> new TruckOrderDoesntExistException("truck order with id :: "+ id+ " doesnt exist"));	
		
		if(truckOrder.getOrderStatus() == OrderStatus.RESERVED) {
			truckOrder.setOrderStatus(OrderStatus.CANCELLED);
		}
		else if(truckOrder.getOrderStatus() == OrderStatus.RENTED) {
			throw new TruckOrderCanNotBeCancelled(" truckOrder with id :: "+ id+ " can not be cancelled, truck already rented out");
		}
		
		
		Storage storage = truckOrder.getStorage();
		storage.setQuantityAtLocation(storage.getQuantityAtLocation() + 1);
		
		Truck truck = truckOrder.getTruck();
		truck.setQuantityAtLocation(truck.getQuantityAtLocation()+ 1);
			
		truckOrder.setOverallTotal(packSuppliesSum);
		
		storageRepository.save(storage);
		truckRepository.save(truck);
		
		truckOrderRepository.save(truckOrder);
	}

}
