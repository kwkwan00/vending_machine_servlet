package com.machine.vending.domain;

import java.math.BigDecimal;

public class ResultResponse {

	private VendorItem item;
	
	private BigDecimal userBalance;
	
	private String displayText;
	
	private boolean successful;
	
	private boolean inProgress;
	
	public ResultResponse() {
		super();
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.successful = isSuccessful;
	}

	public VendorItem getItem() {
		return item;
	}

	public void setItem(VendorItem item) {
		this.item = item;
	}

	public BigDecimal getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultResponse other = (ResultResponse) obj;
		if (displayText == null) {
			if (other.displayText != null)
				return false;
		} else if (!displayText.equals(other.displayText))
			return false;
		if (inProgress != other.inProgress)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (successful != other.successful)
			return false;
		if (userBalance == null) {
			if (other.userBalance != null)
				return false;
		} else if (!userBalance.equals(other.userBalance))
			return false;
		return true;
	}
	
}