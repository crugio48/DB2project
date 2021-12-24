package entities.aaTables;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "aa_total_purchases_per_val", schema = "db2data")
@NamedQuery(name = "TotalPurchasesPerVal.findAll", query = "SELECT t FROM TotalPurchasesPerVal t")
public class TotalPurchasesPerVal implements Serializable {
	private static final long serialVersionUID = 1L;

	public TotalPurchasesPerVal() {
	}
	
	@Id
	private int validity_period_id;
	
	private int service_package_id;
	
	private int number_of_purchases;

	public int getValidity_period_id() {
		return validity_period_id;
	}

	public void setValidity_period_id(int validity_period_id) {
		this.validity_period_id = validity_period_id;
	}

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
