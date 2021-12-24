package entities.aaTables;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table(name = "aa_optionals_total_revenue", schema = "db2data")
@NamedQuery(name = "OptionalsTotalRevenue.findBestSeller", query = "SELECT o FROM OptionalsTotalRevenue o "
		+ "WHERE o.total_revenue = (SELECT MAX(o2.total_revenue) "
								+ "FROM OptionalsTotalRevenue o2)")
public class OptionalsTotalRevenue implements Serializable {
	private static final long serialVersionUID = 1L;

	public OptionalsTotalRevenue() {
	}
	
	@Id
	private int optional_product_id;
	
	private BigDecimal total_revenue;

	
	public int getOptional_product_id() {
		return optional_product_id;
	}

	public void setOptional_product_id(int optional_product_id) {
		this.optional_product_id = optional_product_id;
	}

	public BigDecimal getTotal_revenue() {
		return total_revenue;
	}

	public void setTotal_revenue(BigDecimal total_revenue) {
		this.total_revenue = total_revenue;
	}
	
	
   
}
