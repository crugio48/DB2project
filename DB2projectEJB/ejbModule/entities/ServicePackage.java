package entities;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Service_package
 *
 */
@Entity
@Table(name = "service_package", schema = "db2data")
@NamedQueries({
	@NamedQuery(name = "ServicePackage.findAll", query = "SELECT p FROM ServicePackage p"),
	@NamedQuery(name = "ServicePackage.findByName", query = "SELECT p FROM ServicePackage p WHERE p.name = ?1") 
	})
public class ServicePackage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ServicePackage() {
		super();
	}
	
	public ServicePackage(String name) {
		this.name = name;
		this.optionalProducts = new ArrayList();
		this.services = new ArrayList();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_package_id;
	
	private String name;
	
	@OneToMany(mappedBy = "servicePackage", fetch = FetchType.LAZY)
	private List<Order> orders;
	

	@ManyToMany(mappedBy ="servicePackages")
	private List<OptionalProduct> optionalProducts;
	
	
	@OneToMany(mappedBy = "myServicePackage", cascade = CascadeType.PERSIST)
	private List<Service> services;

	public int getService_package_id() {
		return service_package_id;
	}

	public void setService_package_id(int service_package_id) {
		this.service_package_id = service_package_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<OptionalProduct> getOptionalProducts() {
		return optionalProducts;
	}

	public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
		this.optionalProducts = optionalProducts;
	}
	
	public void addOptionalProduct(OptionalProduct optionalProduct) {
		this.optionalProducts.add(optionalProduct);
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	public void addService(Service service) {
		this.services.add(service);
		service.setMyServicePackage(this);
	}
}
