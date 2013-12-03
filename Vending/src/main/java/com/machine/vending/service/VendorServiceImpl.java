package com.machine.vending.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.machine.vending.domain.CoinReturnResponse;
import com.machine.vending.domain.ResultResponse;
import com.machine.vending.domain.VendorItem;
import com.machine.vending.enums.Currency;
import com.machine.vending.persistence.AdminEntity;
import com.machine.vending.persistence.MachineDAO;
import com.machine.vending.persistence.MachineEntity;
import com.machine.vending.persistence.VendorDAO;
import com.machine.vending.persistence.VendorEntity;

@Component("vendorService")
public class VendorServiceImpl implements VendorService {

	@Resource(name = "vendorDAOHibernate")
	private VendorDAO vendorDAO;
	
	@Resource(name = "machineDAOHibernate")
	private MachineDAO machineDAO;
	
	public VendorServiceImpl() {
		super();
	}
	
	public VendorServiceImpl(VendorDAO vendorDAO, MachineDAO machineDAO) {
		this.vendorDAO = vendorDAO;
		this.machineDAO = machineDAO;
	}
	
	@Transactional
	public List<VendorItem> getAllVendorItems() {
		List<VendorItem> vendorItems = new ArrayList<VendorItem>();
		for (VendorEntity entity : vendorDAO.getAllVendorItems()) {
			vendorItems.add(new VendorItem(entity));
		}
		return vendorItems;
	}
	
	@Transactional
	public BigDecimal getUserBalance() {
		MachineEntity machineEntity = machineDAO.getMachineInfo();
		return machineEntity.getUserBalance();
	}
	
	@Transactional
	public ResultResponse verifyAccountInfo(String username, String password) {
		ResultResponse result = new ResultResponse();
		result.setSuccessful(false);
		result.setDisplayText(INVALID_CREDENTIALS);
		for (AdminEntity admin : machineDAO.getMachineAdministrators()) {
			if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
				result.setSuccessful(true);
				result.setDisplayText(LOGIN_SUCCESSFUL);
			}
		}
		return result;
	}
	
	@Transactional
	public ResultResponse restockItem(Integer id) {
		ResultResponse result = new ResultResponse();
		try {
			VendorEntity entity = vendorDAO.getVendorItem(id);
			entity.setStock(entity.getStock() + 1);
			vendorDAO.updateVendorItem(entity);
			result.setItem(new VendorItem(entity));
			result.setDisplayText(RESTOCK_SUCCESS);
			result.setSuccessful(true);
		} catch (Exception e) {
			result.setDisplayText(TECHNICAL_ERROR);
			result.setSuccessful(false);
		}
		return result;
	}
	
	@Transactional
	public ResultResponse insertCoin(BigDecimal amount) {
		ResultResponse result = new ResultResponse();
		try {
			MachineEntity machineEntity = machineDAO.getMachineInfo();
			machineEntity.setUserBalance(machineEntity.getUserBalance().add(amount));
			machineDAO.updateMachineInfo(machineEntity);
			result.setUserBalance(machineEntity.getUserBalance());
			result.setDisplayText(INSERT_COIN_SUCCESS);
			result.setSuccessful(true);
		} catch (Exception e) {
			result.setDisplayText(TECHNICAL_ERROR);
			result.setSuccessful(false);
		}
		return result;
	}
	
	@Transactional
	public ResultResponse purchaseItem(Integer id) {
		ResultResponse result = new ResultResponse();
		try {
			MachineEntity machineEntity = machineDAO.getMachineInfo();
			VendorEntity vendorEntity = vendorDAO.getVendorItem(id);
			
			if (isPurchaseInvalid(machineEntity, vendorEntity, result)) {
				result.setSuccessful(false);
				return result;
			}
			
			machineEntity.setLocked(true);
			machineDAO.updateMachineInfo(machineEntity);
			
			vendorEntity.setStock(vendorEntity.getStock() - 1);
			vendorDAO.updateVendorItem(vendorEntity);
			
			machineEntity.setUserBalance(machineEntity.getUserBalance().subtract(vendorEntity.getPrice()));
			machineDAO.updateMachineInfo(machineEntity);
			
			wait(20); // Simulate the product dispensing somehow
			
			machineEntity.setLocked(false);
			machineDAO.updateMachineInfo(machineEntity);
			
			result.setItem(new VendorItem(vendorEntity));
			result.setUserBalance(machineEntity.getUserBalance());
			result.setDisplayText(PURCHASE_SUCCESS);
			result.setSuccessful(true);
		} catch (Exception e) {
			result.setDisplayText(TECHNICAL_ERROR);
			result.setSuccessful(false);
		}
		
		return result;
	}
	
	public CoinReturnResponse returnCoin() {
		CoinReturnResponse coinReturn = new CoinReturnResponse();
		
		MachineEntity machineEntity = machineDAO.getMachineInfo();
		BigDecimal remainingBalance = machineEntity.getUserBalance();
		
		coinReturn.setNumQuarters(determineCurrencyCount(remainingBalance, Currency.QUARTER));
		remainingBalance = remainingBalance.remainder(Currency.valueOf(Currency.QUARTER));
		
		coinReturn.setNumDimes(determineCurrencyCount(remainingBalance, Currency.DIME));
		remainingBalance = remainingBalance.remainder(Currency.valueOf(Currency.DIME));
		
		coinReturn.setNumNickels(determineCurrencyCount(remainingBalance, Currency.NICKEL));
		remainingBalance = remainingBalance.remainder(Currency.valueOf(Currency.NICKEL));
		
		machineEntity.setUserBalance(remainingBalance);
		machineDAO.updateMachineInfo(machineEntity);
		
		coinReturn.setUserBalance(remainingBalance);
		
		return coinReturn;
	}
	
	public Integer determineCurrencyCount(BigDecimal remainingBalance, Currency currency) {
		return remainingBalance.divide(Currency.valueOf(currency), RoundingMode.FLOOR).intValue();
	}
	
	public boolean isPurchaseInvalid(MachineEntity machine, VendorEntity vendor, ResultResponse result) {
		boolean successful = false;
		if (machine.isLocked()) {
			result.setInProgress(true);
			successful = true;
		} else if (machine.getUserBalance().compareTo(vendor.getPrice()) < 0) {
			result.setDisplayText(INSUFFICIENT_BALANCE);
			result.setUserBalance(machine.getUserBalance());
			successful = true;
		} else if (!(vendor.getStock() > 0)) {
			result.setDisplayText(OUT_OF_STOCK);
			result.setUserBalance(machine.getUserBalance());
			successful = true;
		}
		return successful;
	}
	
	public static void wait (int n){
        long t0,t1;
        t0=System.currentTimeMillis();
        do{
            t1=System.currentTimeMillis();
        }
        while (t1-t0<1000);
}
	
}