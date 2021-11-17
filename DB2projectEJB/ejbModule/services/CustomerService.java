package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Customer;

@Stateless
public class CustomerService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	
	public CustomerService() {
		
	}
	
	public Customer checkCredentials(String username, String password) {
		List<Customer> customerList = null;
		
		try {
			customerList = em.createNamedQuery("Customer.checkCredentials", Customer.class).setParameter(1, username).setParameter(2, password)
					.getResultList();
			
		} catch(PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		if (customerList.isEmpty()) {
			return null;
		}
		else if (customerList.size() == 1) {
			return customerList.get(0);
		}
		else {
			System.out.println("More than one user registered with same credentials");
			return null;
		}
	}

}
