package com.orbitz.vending.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.springframework.orm.hibernate3.HibernateTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class Persistence_Test {
	
	private HibernateTemplate hibernateTemplate = mock(HibernateTemplate.class);

	private MachineDAO machineDAO;
	private VendorDAO vendorDAO;
	
	@Before
	public void setUp() {
		machineDAO = new MachineDAOHibernate(hibernateTemplate);
		vendorDAO = new VendorDAOHibernate(hibernateTemplate);
	}
	
	@Test
	public void multipleVendorItemsMapping() {
		when(hibernateTemplate.find(VendorDAOHibernate.FROM_VENDOR)).thenReturn(null);
		assertNull(vendorDAO.getAllVendorItems());
		
		when(hibernateTemplate.find(VendorDAOHibernate.FROM_VENDOR)).thenReturn(new ArrayList<VendorEntity>());
		List<VendorEntity> results = vendorDAO.getAllVendorItems();
		assertNotNull(results);
		assertTrue(results.isEmpty());
	}
	
	@Test
	public void singleVendorItemMapping() {
		when(hibernateTemplate.get(VendorEntity.class, 0)).thenReturn(null);
		assertNull(vendorDAO.getVendorItem(0));
		
		when(hibernateTemplate.get(VendorEntity.class, 1)).thenReturn(new VendorEntity());
		VendorEntity result = vendorDAO.getVendorItem(1);
		assertNotNull(result);
		assertEquals(new VendorEntity(), result);
	}
	
	@Test
	public void updateVendorItem() {
		vendorDAO.updateVendorItem(new VendorEntity()); // Just expecting this to not throw Exception
	}
	
	@Test
	public void machineInfo() {
		when(hibernateTemplate.find(MachineDAOHibernate.FROM_MACHINE)).thenReturn(null);
		assertNull(machineDAO.getMachineInfo());
		
		when(hibernateTemplate.find(MachineDAOHibernate.FROM_MACHINE)).thenReturn(new ArrayList<MachineEntity>());
		assertNull(machineDAO.getMachineInfo());
		
		when(hibernateTemplate.find(MachineDAOHibernate.FROM_MACHINE)).thenReturn(Arrays.asList(new MachineEntity[] {new MachineEntity()}));
		MachineEntity result = machineDAO.getMachineInfo();
		assertNotNull(result);
		assertEquals(new MachineEntity(), result);
	}
	
	@Test
	public void updateMachineInfo() {
		machineDAO.updateMachineInfo(new MachineEntity()); // Just expecting this to not throw Exception
	}
	
	@Test
	public void getMachineAdminInfo() {
		when(hibernateTemplate.find(MachineDAOHibernate.FROM_ADMIN)).thenReturn(null);
		assertNull(machineDAO.getMachineAdministrators());
		
		when(hibernateTemplate.find(MachineDAOHibernate.FROM_ADMIN)).thenReturn(new ArrayList<AdminEntity>());
		List<AdminEntity> results = machineDAO.getMachineAdministrators();
		assertNotNull(results);
		assertTrue(results.isEmpty());
	}
	
}