package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.ValidityPeriod;

@Stateless
public class ValidityPeriodService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	
	public ValidityPeriodService() {
		
	}
	
	public List<ValidityPeriod> getAllValidityPeriods(){
		List<ValidityPeriod> validityPeriodList = null;
		
		try {
			validityPeriodList = em.createNamedQuery("ValidityPeriod.findAll", ValidityPeriod.class).getResultList();
			
		} catch(PersistenceException e){
			System.out.println("PersistenceException");
		}

		if (validityPeriodList.isEmpty()) {
			return null;
		}
		else {
			return validityPeriodList;
		}
		
	}
	
	
	public ValidityPeriod getValidityPeriod(int validityPeriodId) {
		return em.find(ValidityPeriod.class, validityPeriodId);
	}
}