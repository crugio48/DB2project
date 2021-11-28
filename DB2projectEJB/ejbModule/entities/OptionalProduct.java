package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Optional_product
 *
 */
@Entity
@Table(name = "optional_product", schema = "db2data")
public class OptionalProduct implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public OptionalProduct() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int optional_product_id;
	
	String name;
	
	int monthly_fee;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "optional_products_selected",
		joinColumns = @JoinColumn(name = "optional_product_id"),
		inverseJoinColumns = @JoinColumn(name = "order_id"))
	Collection<Order> orders;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "optional_products_available",
		joinColumns = @JoinColumn(name = "optional_product_id"),
		inverseJoinColumns = @JoinColumn(name = "service_package_id"))
	Collection<ServicePackage> servicePackages;
	
   
}
