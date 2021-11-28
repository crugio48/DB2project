package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "order", schema = "db2data")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_date;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	private String status;
	
	private int total_value;
	
	@Temporal(TemporalType.DATE)
	private Date deactivation_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "validity_period_id")
	private ValidityPeriod validityPeriod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_package_id")
	private ServicePackage servicePackage;
	
	@ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	private Collection<OptionalProduct> optionalProducts;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotal_value() {
		return total_value;
	}

	public void setTotal_value(int total_value) {
		this.total_value = total_value;
	}

	public Date getDeactivation_date() {
		return deactivation_date;
	}

	public void setDeactivation_date(Date deactivation_date) {
		this.deactivation_date = deactivation_date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ValidityPeriod getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public ServicePackage getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public Collection<OptionalProduct> getOptionalProducts() {
		return optionalProducts;
	}

	public void setOptionalProducts(Collection<OptionalProduct> optionalProducts) {
		this.optionalProducts = optionalProducts;
	}
	
   
}
