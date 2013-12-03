package com.machine.vending.enums;

import java.math.BigDecimal;


public enum Currency {

	DOLLAR(new BigDecimal("1.00")), QUARTER(new BigDecimal("0.25")), 
	DIME(new BigDecimal("0.10")), NICKEL(new BigDecimal("0.05")), 
	PENNY(new BigDecimal("0.01"));
	
	private BigDecimal value;
	
	private Currency(BigDecimal value) {
		this.value = value;
	}
	
	public static BigDecimal valueOf(Currency currency) {
		return currency.value;
	}
	
}