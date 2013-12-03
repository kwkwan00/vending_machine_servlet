package com.machine.vending.domain;

import java.math.BigDecimal;

public class CoinReturnResponse {

	private BigDecimal userBalance;
	
	private Integer numQuarters;
	
	private Integer numDimes;
	
	private Integer numNickels;

	public BigDecimal getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance;
	}

	public Integer getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(Integer numQuarters) {
		this.numQuarters = numQuarters;
	}

	public Integer getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(Integer numDimes) {
		this.numDimes = numDimes;
	}

	public Integer getNumNickels() {
		return numNickels;
	}

	public void setNumNickels(Integer numNickels) {
		this.numNickels = numNickels;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoinReturnResponse other = (CoinReturnResponse) obj;
		if (numDimes == null) {
			if (other.numDimes != null)
				return false;
		} else if (!numDimes.equals(other.numDimes))
			return false;
		if (numNickels == null) {
			if (other.numNickels != null)
				return false;
		} else if (!numNickels.equals(other.numNickels))
			return false;
		if (numQuarters == null) {
			if (other.numQuarters != null)
				return false;
		} else if (!numQuarters.equals(other.numQuarters))
			return false;
		if (userBalance == null) {
			if (other.userBalance != null)
				return false;
		} else if (!userBalance.equals(other.userBalance))
			return false;
		return true;
	}
	
}