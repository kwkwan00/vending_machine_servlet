package com.orbitz.vending.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import com.orbitz.vending.TestUtils;
import com.orbitz.vending.domain.VendorItem;
import com.orbitz.vending.persistence.VendorDAO;
import com.orbitz.vending.persistence.VendorEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class GetAllVendorItems_VendorService_Test {

	private VendorService vendorService;
	private VendorDAO vendorDAO = mock(VendorDAO.class);
	
	@Before
	public void setUp() {
		vendorService = new VendorServiceImpl(vendorDAO, null);
	}
	
	@Test(expected = RuntimeException.class)
	public void exceptionOccurred() {
		when(vendorDAO.getAllVendorItems()).thenThrow(new RuntimeException("error"));
		vendorService.getAllVendorItems();
	}
	
	@Test
	public void noVendorItems() {
		when(vendorDAO.getAllVendorItems()).thenReturn(new ArrayList<VendorEntity>());
		assertTrue(vendorService.getAllVendorItems().isEmpty());
	}
	
	@Test
	public void singleVendorItem() {
		List<VendorEntity> mockedResultSet = new ArrayList<VendorEntity>();
		mockedResultSet.add(TestUtils.createVendorEntity(0));
		when(vendorDAO.getAllVendorItems()).thenReturn(mockedResultSet);
		
		List<VendorItem> actualResult = vendorService.getAllVendorItems();
		assertFalse(actualResult.isEmpty());
		assertEquals(1, actualResult.size());
		
		VendorItem actualElement = actualResult.get(0);
		assertEquals(new Integer(0), actualElement.getId());
		assertEquals("test-path", actualElement.getImagePath());
		assertEquals("test-name", actualElement.getName());
		assertEquals(new BigDecimal(0), actualElement.getPrice());
		assertEquals(new Integer(0), actualElement.getStock());
	}

	@Test
	public void multipleVendorItems() {
		List<VendorEntity> mockedResultSet = new ArrayList<VendorEntity>();
		for (int i = 0; i < 5; i++) mockedResultSet.add(TestUtils.createVendorEntity(i));
		when(vendorDAO.getAllVendorItems()).thenReturn(mockedResultSet);
		
		List<VendorItem> actualResult = vendorService.getAllVendorItems();
		assertFalse(actualResult.isEmpty());
		assertEquals(5, actualResult.size());
		
		
		VendorItem actualElement;
		for (int i = 0; i < 5; i++) {
			actualElement = actualResult.get(i);
			assertEquals(new Integer(i), actualElement.getId());
			assertEquals("test-path", actualElement.getImagePath());
			assertEquals("test-name", actualElement.getName());
			assertEquals(new BigDecimal(i), actualElement.getPrice());
			assertEquals(new Integer(i), actualElement.getStock());
		}
	}
	
}