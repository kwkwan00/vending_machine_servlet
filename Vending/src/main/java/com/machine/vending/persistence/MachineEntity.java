package com.machine.vending.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Machine")
public class MachineEntity {

	private Integer id;
	
	private BigDecimal userBalance;
	
	private boolean locked;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MachineId")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "UserBalance")
	public BigDecimal getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance;
	}

	@Column(name = "Locked")
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MachineEntity other = (MachineEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (locked != other.locked)
			return false;
		if (userBalance == null) {
			if (other.userBalance != null)
				return false;
		} else if (!userBalance.equals(other.userBalance))
			return false;
		return true;
	}
	
}
