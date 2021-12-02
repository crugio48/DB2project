package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.ServicePackage;

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
	
	
	public boolean doesServicePackageExist(int servicePackageId) {
		return (em.find(ServicePackage.class, servicePackageId) != null);
	}
}
