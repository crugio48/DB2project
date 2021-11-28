package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import it.polimi.db2.mission.entities.ValidityPeriod;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "order", schema = "db2data")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int order_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date creation_date;
	
	@Temporal(TemporalType.DATE)
	Date start_date;
	
	String status;
	
	int total_value;
	
	@Temporal(TemporalType.DATE)
	Date deactivation_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "validity_period_id")
	ValidityPeriod validityPeriod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_package_id")
	ServicePackage servicePackage;
	
	@ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	Collection<OptionalProduct> optionalProducts;
	
   
}
