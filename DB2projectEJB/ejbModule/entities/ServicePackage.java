package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Service_package
 *
 */
@Entity
@Table(name = "service_package", schema = "db2data")
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
	private Collection<Order> orders;
	

	@ManyToMany(mappedBy ="servicePackages", fetch = FetchType.LAZY)
	private Collection<OptionalProduct> optionalProducts;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "custom_service",
		joinColumns = @JoinColumn(name = "service_package_id"),
		inverseJoinColumns = @JoinColumn(name = "service_id"))
	private Collection<Service> services;

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

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<OptionalProduct> getOptionalProducts() {
		return optionalProducts;
	}

	public void setOptionalProducts(Collection<OptionalProduct> optionalProducts) {
		this.optionalProducts = optionalProducts;
	}

	public Collection<Service> getServices() {
		return services;
	}

	public void setServices(Collection<Service> services) {
		this.services = services;
	}
	
}
