package com.edgar.uhaul.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.exceptions.PackingSupplyAlreadyExistsException;
import com.edgar.uhaul.exceptions.PackingSupplyDoesntExistException;
import com.edgar.uhaul.models.PackingSupply;
import com.edgar.uhaul.repositories.packingSupplyRepository;

@Service
public class PackingSupplyService {

	@Autowired
	private packingSupplyRepository packingSupplyRepository;

	/* new packingSupply */
	public PackingSupply addNewPackingSupply(PackingSupply packingSupply) {
		if (!isExistsByPackingSupplyName(packingSupply.getSupplyName())) {
			return packingSupplyRepository.save(packingSupply);
		} else
			throw new PackingSupplyAlreadyExistsException(
					"packing supply with name :: " + packingSupply.getSupplyName() + " already exists");

	}
	/* all supplies */
	public List<PackingSupply> getAllPackingSupplies() {
		return packingSupplyRepository.findAll();
	}
	
	/* supplies by name*/
	public PackingSupply getOneBySupplyName(String supplyName) {
		if(isExistsByPackingSupplyName(supplyName)) {
			return packingSupplyRepository.findBySupplyName(supplyName).get();
		}
		else 
			throw new PackingSupplyDoesntExistException("packing supply with name :: "+ supplyName + " doesnt exist");
	}
	
	
	/* supplies by id*/
	public PackingSupply getOneBySupplyId(long id) {
		if(packingSupplyRepository.existsById(id)) {
			return packingSupplyRepository.findById(id).get();
		}
		else 
			throw new PackingSupplyDoesntExistException("packing supply with id :: "+ id + " doesnt exist");
	}
	
	/* delete supply by id */
	public void deleteOneByid(long id ) {
		if(packingSupplyRepository.existsById(id)) {
			 packingSupplyRepository.deleteById(id);
		}
		else 
			throw new PackingSupplyDoesntExistException("packing supply with id :: "+ id + " doesnt exist");
			
	}
	
	

	/* checks */
	private boolean isExistsByPackingSupplyName(String supplyName) {
		return packingSupplyRepository.existsBySupplyName(supplyName) ? true : false;
	}

}
