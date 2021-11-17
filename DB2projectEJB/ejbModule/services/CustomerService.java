package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Customer;

@Stateless
public class CustomerService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	@EJB(name = "services/EmployeeService")
	private EmployeeService employeeService;
	
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
	
	public Customer createCustomer(String username, String password, String email) {
		
		if (this.isCustomerAlreadyPresent(username) || employeeService.isEmployeeAlreadyPresent(username)) {
			return null;
		}
		else {
			Customer customer = new Customer(username, password, email);
			em.persist(customer);
			
			return customer;
		}
		
	}
	
	public boolean isCustomerAlreadyPresent(String username) {
		Customer customer = em.find(Customer.class, username);
		
		
		if (customer != null) {
			return true;
		}
		else {
			return false;
		}
	}

}
