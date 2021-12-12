package services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Customer;
import entities.OptionalProduct;
import entities.Order;
import entities.ServicePackage;
import entities.ValidityPeriod;

@Stateless
public class OrderService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	
	public OrderService() {
		
	}
	
	public void createOrder(
			int servicePackageId,
			int validityPeriodId, 
			Map<Integer,Boolean>optionalsSelected, 
			BigDecimal totalAmount,
			Date startDate,
			String username,
			Date creationDate,
			String status) {
		
		Order order = new Order(creationDate, startDate, status, totalAmount);
		Customer customer = em.find(Customer.class, username);
		ServicePackage servicePackage = em.find(ServicePackage.class, servicePackageId);
		ValidityPeriod validityPeriod = em.find(ValidityPeriod.class, validityPeriodId);
		
		order.setCustomer(customer);
		order.setServicePackage(servicePackage);
		order.setValidityPeriod(validityPeriod);
		
		em.persist(order);
		
		customer.addOrder(order);
		servicePackage.addOrder(order);
		validityPeriod.addOrder(order);
		
		
		for (OptionalProduct o : servicePackage.getOptionalProducts()) {
			if (optionalsSelected.get(o.getOptional_product_id())) {
				o.addOrder(order);
				order.addOptionalProduct(o);
			}
		}
		
		
		
	}
}
