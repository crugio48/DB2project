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
	int service_package_id;
	
	String name;
	
	@OneToMany(mappedBy = "servicePackage", fetch = FetchType.LAZY)
	Collection<Order> orders;
	

	@ManyToMany(mappedBy ="servicePackages", fetch = FetchType.LAZY)
	Collection<OptionalProduct> optionalProducts;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "custom_service",
		joinColumns = @JoinColumn(name = "service_package_id"),
		inverseJoinColumns = @JoinColumn(name = "service_id"))
	Collection<Service> services;
	
	
   
}
