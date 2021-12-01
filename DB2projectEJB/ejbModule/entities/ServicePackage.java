package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Service_package
 *
 */
@Entity
@Table(name = "service_package", schema = "db2data")
@NamedQuery(name = "ServicePackage.findAll", query = "SELECT p FROM ServicePackage p")
public class ServicePackage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ServicePackage() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_package_id;
	
	private String name;
	
	@OneToMany(mappedBy = "servicePackage", fetch = FetchType.LAZY)
	private List<Order> orders;
	

	@ManyToMany(mappedBy ="servicePackages")
	private List<OptionalProduct> optionalProducts;
	
	@ManyToMany
	@JoinTable(name = "custom_service",
		joinColumns = @JoinColumn(name = "service_package_id"),
		inverseJoinColumns = @JoinColumn(name = "service_id"))
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

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	public void addService(Service service) {
		this.services.add(service);
	}
	
	public void addOptionalProduct(OptionalProduct optionalProduct) {
		this.optionalProducts.add(optionalProduct);
	}
}
