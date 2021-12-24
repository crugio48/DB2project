package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Alert;
import entities.aaTables.AverageOptionals;
import entities.aaTables.InsolventUsers;
import entities.aaTables.OptionalsTotalRevenue;
import entities.aaTables.SuspendedOrders;
import entities.aaTables.TotalPurchases;
import entities.aaTables.TotalPurchasesPerVal;
import entities.aaTables.TotalRevenueWithOptionals;

@Stateless
public class AaTablesService {
	@PersistenceContext(unitName = "DB2projectEJB")
	private EntityManager em;
	
	public AaTablesService() {
		
	}
	
	public List<TotalPurchases> getTotalPurchasesOfPackages() {
		List<TotalPurchases> list = null;
		
		try {
			list = em.createNamedQuery("TotalPurchases.findAll", TotalPurchases.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		
		return list;
	}
	
	
	public List<TotalPurchasesPerVal> getTotalPurchasesOfPackagesPerVal() {
		List<TotalPurchasesPerVal> list = null;
		
		try {
			list = em.createNamedQuery("TotalPurchasesPerVal.findAll", TotalPurchasesPerVal.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		
		return list;
	}
	
	
	public List<TotalRevenueWithOptionals> getTotalRevenueWithOptionals(){
		List<TotalRevenueWithOptionals> list = null;
		
		try {
			list = em.createNamedQuery("TotalRevenueWithOptionals.findAll", TotalRevenueWithOptionals.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
			
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		
		return list;
	}
	
	public List<AverageOptionals> getAverageOptionals(){
		List<AverageOptionals> list = null;
		
		try {
			list = em.createNamedQuery("AverageOptionals.findAll", AverageOptionals.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
			
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		return list;
	}
	
	public List<InsolventUsers> getInsolventUsers(){
		List<InsolventUsers> list = null;
		
		try {
			list = em.createNamedQuery("InsolventUsers.findAll", InsolventUsers.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
			
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		
		return list;
	}
	
	
	public List<SuspendedOrders> getSuspendedOrders(){
		List<SuspendedOrders> list = null;
		
		try {
			list = em.createNamedQuery("SuspendedOrders.findAll", SuspendedOrders.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
			
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		
		return list;
	}
	
	
	public List<Alert> getAlerts(){
		List<Alert> list = null;
		
		try {
			list = em.createNamedQuery("Alert.findAll", Alert.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
			
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		return list;
	}
	
	
	public List<OptionalsTotalRevenue> getOptionalsTotalRevenueBestSellers(){
		List<OptionalsTotalRevenue> list = null;
		
		try {
			list = em.createNamedQuery("OptionalsTotalRevenue.findBestSeller", OptionalsTotalRevenue.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
			
		} catch (PersistenceException e) {
			System.out.println("PersistenceException");
		}
		
		
		return list;
	}
	
	
}
