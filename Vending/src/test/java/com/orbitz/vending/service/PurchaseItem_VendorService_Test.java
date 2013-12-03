package com.orbitz.vending.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.orbitz.vending.TestUtils;
import com.orbitz.vending.domain.ResultResponse;
import com.orbitz.vending.domain.VendorItem;
import com.orbitz.vending.persistence.MachineDAO;
import com.orbitz.vending.persistence.MachineEntity;
import com.orbitz.vending.persistence.VendorDAO;
import com.orbitz.vending.persistence.VendorEntity;

public class PurchaseItem_VendorService_Test {

	private VendorService vendorService;
	private VendorDAO vendorDAO = mock(VendorDAO.class);
	private MachineDAO machineDAO = mock(MachineDAO.class);
	
	@Before
	public void setUp() {
		vendorService = new VendorServiceImpl(vendorDAO, machineDAO);
	}
	
	@Test
	public void exceptionOccurred() {
		when(vendorDAO.getVendorItem(0)).thenThrow(new RuntimeException("error"));
		ResultResponse actualResult = vendorService.purchaseItem(0);
		
		assertFalse(actualResult.isSuccessful());
		assertEquals(VendorService.TECHNICAL_ERROR, actualResult.getDisplayText());
		assertNull(actualResult.getItem());
	}
	
	@Test
	public void purchaseItemNotLocked() {
		VendorEntity vendorEntity = TestUtils.createVendorEntity(1);
		vendorEntity.setStock(100);
		when(vendorDAO.getVendorItem(1)).thenReturn(vendorEntity);
		
		MachineEntity machineEntity = TestUtils.createMachineEntity(false);
		machineEntity.setUserBalance(BigDecimal.ONE);
		when(machineDAO.getMachineInfo()).thenReturn(machineEntity);
		
		ResultResponse actualResult = vendorService.purchaseItem(1);
		
		assertTrue(actualResult.isSuccessful());
		assertEquals(VendorService.PURCHASE_SUCCESS, actualResult.getDisplayText());
		
		VendorItem actualElement = actualResult.getItem();
		assertNotNull(actualElement);
		assertEquals(new Integer(99), actualElement.getStock());
	}
	
	@Test
	public void purchaseInsufficientBalance() {
		VendorEntity vendorEntity = TestUtils.createVendorEntity(1);
		vendorEntity.setPrice(BigDecimal.ONE);
		vendorEntity.setStock(100);
		when(vendorDAO.getVendorItem(1)).thenReturn(vendorEntity);
		
		MachineEntity machineEntity = TestUtils.createMachineEntity(false);
		machineEntity.setUserBalance(BigDecimal.ZERO);
		when(machineDAO.getMachineInfo()).thenReturn(machineEntity);
		
		ResultResponse actualResult = vendorService.purchaseItem(1);
		
		assertFalse(actualResult.isSuccessful());
		assertEquals(VendorService.INSUFFICIENT_BALANCE, actualResult.getDisplayText());
		
		assertNull(actualResult.getItem());
	}
	
	@Test
	public void purchaseItemLocked() {
		VendorEntity vendorEntity = TestUtils.createVendorEntity(1);
		vendorEntity.setStock(100);
		when(vendorDAO.getVendorItem(1)).thenReturn(vendorEntity);
		
		MachineEntity machineEntity = TestUtils.createMachineEntity(true);
		when(machineDAO.getMachineInfo()).thenReturn(machineEntity);
		
		ResultResponse actualResult = vendorService.purchaseItem(1);
		
		assertFalse(actualResult.isSuccessful());
		assertTrue(actualResult.isInProgress());
	}
	
	@Test
	public void purchaseItemOutOfStock() {
		VendorEntity vendorEntity = TestUtils.createVendorEntity(1);
		vendorEntity.setPrice(BigDecimal.ONE);
		vendorEntity.setStock(0);
		when(vendorDAO.getVendorItem(1)).thenReturn(vendorEntity);
		
		MachineEntity machineEntity = TestUtils.createMachineEntity(false);
		machineEntity.setUserBalance(BigDecimal.TEN);
		when(machineDAO.getMachineInfo()).thenReturn(machineEntity);
		
		ResultResponse actualResult = vendorService.purchaseItem(1);
		
		assertFalse(actualResult.isSuccessful());
		assertEquals(VendorService.OUT_OF_STOCK, actualResult.getDisplayText());
		
		assertNull(actualResult.getItem());
	}
	
}