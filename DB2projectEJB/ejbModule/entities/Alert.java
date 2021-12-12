package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "alert", schema = "db2data")
public class Alert implements Serializable {
	private static final long serialVersionUID = 1L;


	public Alert() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int alert_id;
	
	private String email;
	
	private BigDecimal amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_and_time;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	private Customer customer;
	

	public int getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(int alert_id) {
		this.alert_id = alert_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate_and_time() {
		return date_and_time;
	}

	public void setDate_and_time(Date date_and_time) {
		this.date_and_time = date_and_time;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
   
}
