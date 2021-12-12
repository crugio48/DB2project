package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "order", schema = "db2data")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}
	
	
	
	public Order(Date creation_date, Date start_date, String status, BigDecimal total_value) {
		this.creation_date = creation_date;
		this.start_date = start_date;
		this.status = status;
		this.total_value = total_value;
		this.optionalProducts = new ArrayList();
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_date;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	private String status;
	
	private BigDecimal total_value;
	
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
	private List<OptionalProduct> optionalProducts;

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

	public BigDecimal getTotal_value() {
		return total_value;
	}

	public void setTotal_value(BigDecimal total_value) {
		this.total_value = total_value;
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

	public List<OptionalProduct> getOptionalProducts() {
		return optionalProducts;
	}

	public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
		this.optionalProducts = optionalProducts;
	}
	
	public void addOptionalProduct(OptionalProduct optionalProduct) {
		this.optionalProducts.add(optionalProduct);
	}
   
}
