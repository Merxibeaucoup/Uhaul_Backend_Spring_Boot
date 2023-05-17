package com.edgar.uhaul.services.cart;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.LocationDoesntExistException;
import com.edgar.uhaul.exceptions.StorageInsuranceDoesntExistException;
import com.edgar.uhaul.exceptions.StorageOrderDoesNotExistException;
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

import jakarta.transaction.Transactional;

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
	@Transactional
	public StorageOrder newStorageOrder(StorageOrderRequest orderRequest, User user) {

		StorageOrder storageOrder = new StorageOrder();

		StorageInsurance insurance = storageInsuranceRepository.findByName(orderRequest.getStorageInsurance())
				.orElseThrow(() -> new StorageInsuranceDoesntExistException(
						"Storage insurance with name :: " + orderRequest.getStorageInsurance() + " doesnt exist"));

		storageOrder.setPropertyInsurance(insurance);

		Location location = locationRepository.findByLocationName(orderRequest.getMoveInLocation())
				.orElseThrow(() -> new LocationDoesntExistException(
						"location with name :: " + orderRequest.getMoveInLocation() + " doesnt exist"));
		storageOrder.setStorageLocation(location);

		// List solves query did not return a unique result
		// java.persistence.NonUniqueResultException
		List<Storage> storage = storageRepository.findByStorageName(orderRequest.getStorageName());

		storage.forEach(s -> {
			s.setQuantityAtLocation(s.getQuantityAtLocation() - 1);
			storageOrder.setStorageName(s);
			totalDueToday = s.getMonthlyFee().add(insurance.getPrice());
		});

		if (orderRequest.getMoveInDate().equals(today)) {
			storageOrder.setOrderStatus(OrderStatus.RENTED);
			storageOrder.setIsMovedIn(true);
			storageOrder.setIsMovedOut(false);
			storageOrder.setIsCancelled(false);
		} else if (orderRequest.getMoveInDate().isAfter(today)) {
			storageOrder.setOrderStatus(OrderStatus.RESERVED);
			storageOrder.setIsMovedIn(false);
			storageOrder.setIsMovedOut(false);
			storageOrder.setIsCancelled(false);
		}

		storageOrder.setMoveInDate(orderRequest.getMoveInDate());

		storageOrder.setClient(user);

		storageOrder.setTotalDueToday(totalDueToday);

		return storageOrderRepository.save(storageOrder);

	}

	/* all storage orders */
	public List<StorageOrder> allStorageOrders() {
		return storageOrderRepository.findAll();
	}

	/* get storage Order by id */
	public StorageOrder getStorageOrderById(Long id) {
		return storageOrderRepository.findById(id).get();
	}

	/* move in to storage unit */
	public void moveIntoStorageUnit(Long id) {
		StorageOrder storageOrder = storageOrderRepository.findById(id).orElseThrow(
				() -> new StorageOrderDoesNotExistException("storage order with id :: " + id + "does not exist"));

		if (storageOrder.getOrderStatus() == OrderStatus.RESERVED
				&& (storageOrder.getMoveInDate().isEqual(today) || storageOrder.getMoveInDate().isAfter(today))) {
			storageOrder.setIsMovedIn(true);
		} else
			throw new StorageOrderDoesNotExistException(
					"storage order doesnt exist, its either already canceled or unit is already move in , or move in date is not today or after today");

		storageOrderRepository.save(storageOrder);
	}

	/* storage order cancel reserved order */
	@Transactional
	public void cancelReservedStorage(Long id) {
		StorageOrder storageOrder = storageOrderRepository.findById(id).orElseThrow(
				() -> new StorageOrderDoesNotExistException("storage order with id :: " + id + "does not exist"));

		if (storageOrder.getOrderStatus() == OrderStatus.RESERVED && storageOrder.getIsMovedIn() == false) {
			storageOrder.setIsCancelled(true);
		} else
			throw new StorageOrderDoesNotExistException(
					"storage order doesnt exist, its either already canceled or unit is already rented out");

		storageOrderRepository.save(storageOrder);
	}

	/* move out of of rental unit */
	@Transactional
	public void moveOutOfStorage(Long id) {
		StorageOrder storageOrder = storageOrderRepository.findById(id).orElseThrow(
				() -> new StorageOrderDoesNotExistException("storage order with id :: " + id + "does not exist"));

		if (storageOrder.getOrderStatus() == OrderStatus.RENTED && storageOrder.getIsMovedOut() == false) {
			storageOrder.setIsMovedOut(true);
		} else
			throw new StorageOrderDoesNotExistException(
					"storage order doesnt exist, its either canceled or unit is already moved out");

		storageOrderRepository.save(storageOrder);
	}

	/* scheduler to send bill every 30days -> not done !!!! */
	BigDecimal dueToday;
	String email ="";
	public void sendInvoice() {
		
		List<StorageOrder> storageOrders = storageOrderRepository.findAll()
				.stream()
				.filter(s ->
				s.getStorageName() != null
				&& s.getIsMovedOut() !=null
				&& s.getIsMovedOut() != true 
				&& s.getOrderStatus() != null
				&& s.getOrderStatus() == OrderStatus.RENTED
						)
				.collect(Collectors.toList());
		
		storageOrders.stream()
		.forEach(t -> {
			email = t.getClient().getEmail(); // use for mail sender !
			dueToday = t.getStorageName().getMonthlyFee();
		});
	}

}
