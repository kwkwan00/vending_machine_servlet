package com.orbitz.vending.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.orbitz.vending.TestUtils;
import com.orbitz.vending.persistence.MachineDAO;
import com.orbitz.vending.persistence.MachineEntity;

public class GetUserBalance_VendorService_Test {

	private VendorService vendorService;
	private MachineDAO machineDAO = mock(MachineDAO.class);
	
	@Before
	public void setUp() {
		vendorService = new VendorServiceImpl(null, machineDAO);
	}
	
	@Test(expected = RuntimeException.class)
	public void exceptionOccurred() {
		when(machineDAO.getMachineInfo()).thenThrow(new RuntimeException("error"));
		vendorService.getUserBalance();
	}
	
	@Test
	public void getUserBalance() {
		MachineEntity entity = TestUtils.createMachineEntity(false);
		entity.setUserBalance(new BigDecimal("2.50"));
		when(machineDAO.getMachineInfo()).thenReturn(entity);
		assertEquals(new BigDecimal("2.50"), vendorService.getUserBalance());
	}
	
}