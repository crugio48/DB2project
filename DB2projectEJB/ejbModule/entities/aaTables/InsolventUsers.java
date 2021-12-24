package entities.aaTables;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "aa_insolvent_users", schema = "db2data")
@NamedQuery(name = "InsolventUsers.findAll", query = "SELECT i FROM InsolventUsers i")
public class InsolventUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	public InsolventUsers() {
	}
	
	@Id
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
   
}
