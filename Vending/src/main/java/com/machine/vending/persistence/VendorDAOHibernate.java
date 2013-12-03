package com.machine.vending.persistence;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("vendorDAOHibernate")
public class VendorDAOHibernate implements VendorDAO {

	public static final String FROM_VENDOR = "from VendorEntity";
	
	private HibernateTemplate hibernateTemplate;
	
	public VendorDAOHibernate() {
		super();
	}
	
	public VendorDAOHibernate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<VendorEntity> getAllVendorItems() {
		return (List<VendorEntity>) hibernateTemplate.find(FROM_VENDOR);
	}
	
	public VendorEntity getVendorItem(Integer id) {
		return hibernateTemplate.get(VendorEntity.class, id);
	}
	
	public void updateVendorItem(VendorEntity entity) throws DataAccessException {
		hibernateTemplate.save(entity);
	}
	
}