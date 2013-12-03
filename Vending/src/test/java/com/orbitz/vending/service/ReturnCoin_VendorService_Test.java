package com.orbitz.vending.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.orbitz.vending.TestUtils;
import com.orbitz.vending.domain.CoinReturnResponse;
import com.orbitz.vending.persistence.MachineDAO;
import com.orbitz.vending.persistence.MachineEntity;

public class ReturnCoin_VendorService_Test {

	private MachineDAO machineDAO = mock(MachineDAO.class);
	
	private VendorService vendorService = new VendorServiceImpl(null, machineDAO);
	
	@Before
	public void setUp() {
		vendorService = new VendorServiceImpl(null, machineDAO);
	}
	
	@Test(expected = RuntimeException.class)
	public void exceptionOccurred() {
		when(machineDAO.getMachineInfo()).thenThrow(new RuntimeException("error"));
		vendorService.returnCoin();
	}
	
	@Test
	public void returnCoinQuartersOnly() {
		MachineEntity machine = TestUtils.createMachineEntity(false);
		machine.setUserBalance(new BigDecimal("1.00"));
		when(machineDAO.getMachineInfo()).thenReturn(machine);
		CoinReturnResponse coins = vendorService.returnCoin();
		assertEquals(new Integer(4), coins.getNumQuarters());
		assertEquals(new Integer(0), coins.getNumDimes());
		assertEquals(new Integer(0), coins.getNumNickels());
	}
	
	@Test
	public void returnCoinDimesOnly() {
		MachineEntity machine = TestUtils.createMachineEntity(false);
		machine.setUserBalance(new BigDecimal("0.20"));
		when(machineDAO.getMachineInfo()).thenReturn(machine);
		CoinReturnResponse coins = vendorService.returnCoin();
		assertEquals(new Integer(0), coins.getNumQuarters());
		assertEquals(new Integer(2), coins.getNumDimes());
		assertEquals(new Integer(0), coins.getNumNickels());
	}
	
	@Test
	public void returnCoinNickelsOnly() {
		MachineEntity machine = TestUtils.createMachineEntity(false);
		machine.setUserBalance(new BigDecimal("0.05"));
		when(machineDAO.getMachineInfo()).thenReturn(machine);
		CoinReturnResponse coins = vendorService.returnCoin();
		assertEquals(new Integer(0), coins.getNumQuarters());
		assertEquals(new Integer(0), coins.getNumDimes());
		assertEquals(new Integer(1), coins.getNumNickels());
	}
	
	@Test
	public void returnMixedCoins1() {
		MachineEntity machine = TestUtils.createMachineEntity(false);
		machine.setUserBalance(new BigDecimal("0.85"));
		when(machineDAO.getMachineInfo()).thenReturn(machine);
		CoinReturnResponse coins = vendorService.returnCoin();
		assertEquals(new Integer(3), coins.getNumQuarters());
		assertEquals(new Integer(1), coins.getNumDimes());
		assertEquals(new Integer(0), coins.getNumNickels());
	}
	
	@Test
	public void returnMixedCoins2() {
		MachineEntity machine = TestUtils.createMachineEntity(false);
		machine.setUserBalance(new BigDecimal("0.15"));
		when(machineDAO.getMachineInfo()).thenReturn(machine);
		CoinReturnResponse coins = vendorService.returnCoin();
		assertEquals(new Integer(0), coins.getNumQuarters());
		assertEquals(new Integer(1), coins.getNumDimes());
		assertEquals(new Integer(1), coins.getNumNickels());
	}
	
	@Test
	public void returnMixedCoins3() {
		MachineEntity machine = TestUtils.createMachineEntity(false);
		machine.setUserBalance(new BigDecimal("0.30"));
		when(machineDAO.getMachineInfo()).thenReturn(machine);
		CoinReturnResponse coins = vendorService.returnCoin();
		assertEquals(new Integer(1), coins.getNumQuarters());
		assertEquals(new Integer(0), coins.getNumDimes());
		assertEquals(new Integer(1), coins.getNumNickels());
	}
	
	@Test
	public void returnMixedCoins4() {
		MachineEntity machine = TestUtils.createMachineEntity(false);
		machine.setUserBalance(new BigDecimal("0.65"));
		when(machineDAO.getMachineInfo()).thenReturn(machine);
		CoinReturnResponse coins = vendorService.returnCoin();
		assertEquals(new Integer(2), coins.getNumQuarters());
		assertEquals(new Integer(1), coins.getNumDimes());
		assertEquals(new Integer(1), coins.getNumNickels());
	}
	
}