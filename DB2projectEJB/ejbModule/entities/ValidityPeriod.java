package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "validity_period", schema = "db2data")
@NamedQuery(name = "ValidityPeriod.findAll", query = "SELECT v FROM ValidityPeriod v")
public class ValidityPeriod implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ValidityPeriod() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int validity_period_id;
	
	private BigDecimal monthly_fee;
	
	private int duration;
	
	@OneToMany(mappedBy = "validityPeriod", fetch = FetchType.LAZY)
	List<Order> orders;
	

	public int getValidity_period_id() {
		return validity_period_id;
	}

	public void setValidity_period_id(int validity_period_id) {
		this.validity_period_id = validity_period_id;
	}

	public BigDecimal getMonthly_fee() {
		return monthly_fee;
	}

	public void setMonthly_fee(BigDecimal monthly_fee) {
		this.monthly_fee = monthly_fee;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
   
	public void addOrder(Order order) {
		this.orders.add(order);
	}
}
