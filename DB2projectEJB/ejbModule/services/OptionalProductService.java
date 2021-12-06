package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.OptionalProduct;

@Stateless
public class OptionalProductService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	
	public OptionalProductService() {
		
	}

	
	public List<OptionalProduct> getAllOptionalProducts(){
		List<OptionalProduct> optionalProductsList = null;
		
		try {
			optionalProductsList = em.createNamedQuery("OptionalProduct.findAll", OptionalProduct.class).getResultList();
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		if (optionalProductsList.isEmpty()) {
			return null;
		}
		else {
			return optionalProductsList;
		}
	}
	
}
