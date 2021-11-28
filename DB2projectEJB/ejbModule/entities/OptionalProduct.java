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
	private int optional_product_id;
	
	private String name;
	
	private int monthly_fee;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "optional_products_selected",
		joinColumns = @JoinColumn(name = "optional_product_id"),
		inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Collection<Order> orders;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "optional_products_available",
		joinColumns = @JoinColumn(name = "optional_product_id"),
		inverseJoinColumns = @JoinColumn(name = "service_package_id"))
	private Collection<ServicePackage> servicePackages;

	public int getOptional_product_id() {
		return optional_product_id;
	}

	public void setOptional_product_id(int optional_product_id) {
		this.optional_product_id = optional_product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMonthly_fee() {
		return monthly_fee;
	}

	public void setMonthly_fee(int monthly_fee) {
		this.monthly_fee = monthly_fee;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(Collection<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}
	
    
}
