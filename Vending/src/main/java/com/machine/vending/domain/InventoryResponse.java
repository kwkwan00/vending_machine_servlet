package com.machine.vending.domain;

import java.math.BigDecimal;
import java.util.List;

public class InventoryResponse {

	private BigDecimal userBalance;
	
	private List<VendorItem> inventory;

	public BigDecimal getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance;
	}

	public List<VendorItem> getInventory() {
		return inventory;
	}

	public void setInventory(List<VendorItem> inventory) {
		this.inventory = inventory;
	}
	
}