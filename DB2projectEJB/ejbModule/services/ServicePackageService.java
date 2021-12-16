package services;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.OptionalProduct;
import entities.Service;
import entities.ServicePackage;
import entities.ValidityPeriod;

@Stateless
public class ServicePackageService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	
	public ServicePackageService() {
		
	}

	
	public List<ServicePackage> getAllAvailableServicePackages(){
		List<ServicePackage> packagesList = null;
		
		try {
			packagesList = em.createNamedQuery("ServicePackage.findAll", ServicePackage.class).getResultList();
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		if (packagesList.isEmpty()) {
			return null;
		}
		else {
			return packagesList;
		}
	}
	
	
	public ServicePackage getServicePackage(int servicePackageId) {
		return em.find(ServicePackage.class, servicePackageId);
	}
	
	
	public boolean isNameAlreadyPresent(String name) {
		List<ServicePackage> packagesList = null;
		
		try {
			packagesList = em.createNamedQuery("ServicePackage.findByName", ServicePackage.class).setParameter(1, name).getResultList();
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		if (packagesList.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public void createServicePackage(String name, List<Service> allServices, List<ValidityPeriod> validityPeriods,
			Map<Integer,Boolean> optionalsSelected, List<OptionalProduct> allOptionals) {
		
		ServicePackage newPackage = new ServicePackage(name);
		
		for(Service s: allServices) {
			//the add is double patty burger
			newPackage.addService(s);
		}
		
		for(ValidityPeriod v: validityPeriods) {
			//the add is double patty burger
			newPackage.addValidityPeriod(v);
		}
		
		//by cascading also all services will be persisted (among also the subclasses)
		em.persist(newPackage);
		
		
		for (OptionalProduct p : allOptionals) {
			if (optionalsSelected.get(p.getOptional_product_id())) {
				newPackage.addOptionalProduct(p);
				p.addServicePackage(newPackage);
				em.merge(p);
			}
		}
	}
	
}
