package com.orbitz.vending;

import java.math.BigDecimal;

import com.orbitz.vending.persistence.AdminEntity;
import com.orbitz.vending.persistence.MachineEntity;
import com.orbitz.vending.persistence.VendorEntity;

public class TestUtils {

	public static VendorEntity createVendorEntity(Integer id) {
		VendorEntity mockResult = new VendorEntity();
		mockResult.setId(id);
		mockResult.setImagePath("test-path");
		mockResult.setName("test-name");
		mockResult.setPrice(new BigDecimal(id));
		mockResult.setStock(id);
		return mockResult;
	}
	
	public static MachineEntity createMachineEntity(boolean locked) {
		MachineEntity mockResult = new MachineEntity();
		mockResult.setId(1);
		mockResult.setUserBalance(BigDecimal.ZERO);
		mockResult.setLocked(locked);
		return mockResult;
	}
	
	public static AdminEntity createAdminEntity(String username, String password) {
		AdminEntity mockResult = new AdminEntity();
		mockResult.setId(0);
		mockResult.setUsername(username);
		mockResult.setPassword(password);
		return mockResult;
	}
	
}