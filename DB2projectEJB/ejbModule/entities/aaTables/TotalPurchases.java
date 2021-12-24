package entities.aaTables;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "aa_total_purchases", schema = "db2data")
@NamedQuery(name = "TotalPurchases.findAll", query = "SELECT t FROM TotalPurchases t")
public class TotalPurchases implements Serializable {
	private static final long serialVersionUID = 1L;

	public TotalPurchases() {
	}
	
	@Id
	private int service_package_id;
	
	private int number_of_purchases;

	
	public int getService_package_id() {
		return service_package_id;
	}

	public void setService_package_id(int service_package_id) {
		this.service_package_id = service_package_id;
	}

	public int getNumber_of_purchases() {
		return number_of_purchases;
	}

	public void setNumber_of_purchases(int number_of_purchases) {
		this.number_of_purchases = number_of_purchases;
	}
	
	
   
}
