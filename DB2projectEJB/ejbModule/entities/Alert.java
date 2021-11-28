package entities;

import java.io.Serializable;
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
	int alert_id;
	
	int amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date date_and_time;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	Customer customer;
	
	
	
   
}
