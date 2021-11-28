package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;


@Entity
@Table(name = "customer", schema = "db2data")
@NamedQuery(name = "Customer.checkCredentials", query = "SELECT c FROM Customer c WHERE c.username = ?1 and c.password = ?2")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	public Customer() {
	}
	
	public Customer(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	@Id
	private String username;
   
	private String password;
	
	private String email;
	
	private int times_rejected;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.MERGE, orphanRemoval = true)
	Alert alert;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	Collection<Order> orders;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTimes_rejected() {
		return times_rejected;
	}

	public void setTimes_rejected(int times_rejected) {
		this.times_rejected = times_rejected;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	
	
}
