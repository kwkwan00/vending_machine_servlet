package com.machine.vending.persistence;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VendorDAO {

	List<VendorEntity> getAllVendorItems();
	
	VendorEntity getVendorItem(Integer id);
	
	void updateVendorItem(VendorEntity entity) throws DataAccessException;
	
}
