package com.machine.vending.persistence;

import java.util.List;

public interface MachineDAO {

	MachineEntity getMachineInfo();
	
	void updateMachineInfo(MachineEntity entity);
	
	List<AdminEntity> getMachineAdministrators();
	
}