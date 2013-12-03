package com.orbitz.vending.service;



import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.orbitz.vending.TestUtils;
import com.orbitz.vending.domain.ResultResponse;
import com.orbitz.vending.persistence.AdminEntity;
import com.orbitz.vending.persistence.MachineDAO;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VerifyAccountInfo_VendorService_Test {

	private VendorService vendorService;
	private MachineDAO machineDAO = mock(MachineDAO.class);
	
	@Before
	public void setUp() {
		vendorService = new VendorServiceImpl(null, machineDAO);
	}
	
	@Test
	public void noAccountsFound() {
		when(machineDAO.getMachineAdministrators()).thenReturn(new ArrayList<AdminEntity>());
		ResultResponse result = vendorService.verifyAccountInfo("user", "pass");
		assertFalse(result.isSuccessful());
		assertEquals(VendorService.INVALID_CREDENTIALS, result.getDisplayText());
	}
	
	@Test
	public void invalidCredentials() {
		when(machineDAO.getMachineAdministrators()).thenReturn(new ArrayList<AdminEntity>());
		ResultResponse result = vendorService.verifyAccountInfo("user", "pass");
		assertFalse(result.isSuccessful());
		assertEquals(VendorService.INVALID_CREDENTIALS, result.getDisplayText());
	}
	
	@Test
	public void validCredentials() {
		List<AdminEntity> mockedResultSet = new ArrayList<AdminEntity>();
		mockedResultSet.add(TestUtils.createAdminEntity("user", "pass"));
		when(machineDAO.getMachineAdministrators()).thenReturn(mockedResultSet);
		ResultResponse result = vendorService.verifyAccountInfo("user", "pass");
		assertTrue(result.isSuccessful());
		assertEquals(VendorService.LOGIN_SUCCESSFUL, result.getDisplayText());
	}
	
}