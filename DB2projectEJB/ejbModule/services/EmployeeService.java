package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Employee;

@Stateless
public class EmployeeService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	
	public EmployeeService() {
		
	}
	
	public Employee checkCredentials(String username, String password) {
		List<Employee> employeeList = null;
		
		try {
			employeeList = em.createNamedQuery("Employee.checkCredentials", Employee.class).setParameter(1, username).setParameter(2, password)
					.getResultList();
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		if (employeeList.isEmpty()) {
			return null;
		}
		else if (employeeList.size() == 1) {
			return employeeList.get(0);
		}
		else {
			System.out.println("More than one user registered with same credentials");
			return null;
		}
	}
	
}
