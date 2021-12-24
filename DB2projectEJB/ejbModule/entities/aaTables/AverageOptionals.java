package entities.aaTables;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table(name = "aa_average_optionals", schema = "db2data")
@NamedQuery(name = "AverageOptionals.findAll", query = "SELECT a FROM AverageOptionals a")
public class AverageOptionals implements Serializable {
	private static final long serialVersionUID = 1L;

	public AverageOptionals() {
	}
	
	@Id
	private int service_package_id;
	
	private BigDecimal average_number_of_optionals;
	
	private int number_of_purchases;

	public int getService_package_id() {
		return service_package_id;
	}

	public void setService_package_id(int service_package_id) {
		this.service_package_id = service_package_id;
	}

	public BigDecimal getAverage_number_of_optionals() {
		return average_number_of_optionals;
	}

	public void setAverage_number_of_optionals(BigDecimal average_number_of_optionals) {
		this.average_number_of_optionals = average_number_of_optionals;
	}

	public int getNumber_of_purchases() {
		return number_of_purchases;
	}

	public void setNumber_of_purchases(int number_of_purchases) {
		this.number_of_purchases = number_of_purchases;
	}
	
	
   
}
