package com.orbitz.vending.service;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.orbitz.vending.TestUtils;
import com.orbitz.vending.domain.ResultResponse;
import com.orbitz.vending.persistence.MachineDAO;
import com.orbitz.vending.persistence.MachineEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

public class InsertCoin_VendorService_Test {

	private VendorService vendorService;
	private MachineDAO machineDAO = mock(MachineDAO.class);
	
	@Before
	public void setUp() {
		vendorService = new VendorServiceImpl(null, machineDAO);
	}
	
	@Test
	public void exceptionOccurred() {
		when(machineDAO.getMachineInfo()).thenThrow(new RuntimeException("error"));
		ResultResponse response = vendorService.insertCoin(BigDecimal.ONE);
		assertEquals(VendorService.TECHNICAL_ERROR, response.getDisplayText());
	}
	
	@Test
	public void insertCoin() {
		MachineEntity entity = TestUtils.createMachineEntity(false);
		entity.setUserBalance(new BigDecimal("0.25"));
		when(machineDAO.getMachineInfo()).thenReturn(entity);
		ResultResponse response = vendorService.insertCoin(new BigDecimal("0.25"));
		assertEquals(new BigDecimal("0.50"), response.getUserBalance());
	}
	
}