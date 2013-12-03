package com.orbitz.vending.service;

import org.junit.Before;
import org.junit.Test;

import com.orbitz.vending.TestUtils;
import com.orbitz.vending.domain.ResultResponse;
import com.orbitz.vending.domain.VendorItem;
import com.orbitz.vending.persistence.VendorDAO;
import com.orbitz.vending.persistence.VendorEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class RestockItem_VendorService_Test {

	private VendorService vendorService;
	private VendorDAO vendorDAO = mock(VendorDAO.class);
	
	@Before
	public void setUp() {
		vendorService = new VendorServiceImpl(vendorDAO, null);
	}
	
	@Test
	public void exceptionOccurred() {
		when(vendorDAO.getVendorItem(0)).thenThrow(new RuntimeException("error"));
		
		ResultResponse actualResult = vendorService.restockItem(0);
		
		assertFalse(actualResult.isSuccessful());
		assertEquals(VendorService.TECHNICAL_ERROR, actualResult.getDisplayText());
		assertNull(actualResult.getItem());
	}
	
	@Test
	public void restockItem() {
		VendorEntity testEntity = TestUtils.createVendorEntity(1);
		testEntity.setStock(100);
		when(vendorDAO.getVendorItem(1)).thenReturn(testEntity);
		
		ResultResponse actualResult = vendorService.restockItem(1);
		
		assertTrue(actualResult.isSuccessful());
		assertEquals(VendorService.RESTOCK_SUCCESS, actualResult.getDisplayText());
		
		VendorItem actualElement = actualResult.getItem();
		assertNotNull(actualElement);
		assertEquals(new Integer(101), actualElement.getStock());
	}
	
}
