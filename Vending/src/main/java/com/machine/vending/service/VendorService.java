package com.machine.vending.service;

import java.math.BigDecimal;
import java.util.List;

import com.machine.vending.domain.CoinReturnResponse;
import com.machine.vending.domain.ResultResponse;
import com.machine.vending.domain.VendorItem;

public interface VendorService {
	
	public static final String INVALID_CREDENTIALS = "Invalid username/password.";
	public static final String INSERT_COIN_SUCCESS = "Insert coin successful.";
	public static final String INSUFFICIENT_BALANCE = "Insufficient balance for purchase.";
	public static final String LOGIN_SUCCESSFUL = "Administration login Successful";
	public static final String OUT_OF_STOCK = "Out of stock.";
	public static final String PURCHASE_SUCCESS = "Item purchased successfully.";
	public static final String RESTOCK_SUCCESS = "Item stocked successfully.";
	public static final String TECHNICAL_ERROR = "A technical error has occurred.";

	List<VendorItem> getAllVendorItems();
	
	BigDecimal getUserBalance();
	
	ResultResponse verifyAccountInfo(String username, String password);
	
	ResultResponse restockItem(Integer id);
	
	ResultResponse insertCoin(BigDecimal amount);
	
	ResultResponse purchaseItem(Integer id);
	
	CoinReturnResponse returnCoin();
	
}
