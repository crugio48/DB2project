package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "employee", schema = "db2data")
@NamedQuery(name = "Employee.checkCredentials", query = "SELECT e FROM Employee e  WHERE e.username = ?1 and e.password = ?2")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	public Employee() {
		super();
	}
	
	@Id
	private String username;
	
	private String password;

	
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
	
	
   
}
