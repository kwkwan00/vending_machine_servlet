package com.machine.vending.persistence;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("machineDAOHibernate")
public class MachineDAOHibernate implements MachineDAO {

	public static final String FROM_ADMIN = "from AdminEntity";
	public static final String FROM_MACHINE = "From MachineEntity";
	
	private HibernateTemplate hibernateTemplate;

	public MachineDAOHibernate() {
		super();
	}
	
	public MachineDAOHibernate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public MachineEntity getMachineInfo() {
		List<MachineEntity> list = hibernateTemplate.find(FROM_MACHINE);
		if (list == null || list.isEmpty()) return null;
		return list.get(0);
	}
	
	public void updateMachineInfo(MachineEntity entity) {
		hibernateTemplate.update(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<AdminEntity> getMachineAdministrators() {
		return (List<AdminEntity>) hibernateTemplate.find(FROM_ADMIN);
	}
	
}