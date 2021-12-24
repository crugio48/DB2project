package entities.aaTables;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table(name = "aa_total_revenue_with_optionals", schema = "db2data")
@NamedQuery(name = "TotalRevenueWithOptionals.findAll", query = "SELECT t FROM TotalRevenueWithOptionals t")
public class TotalRevenueWithOptionals implements Serializable {
	private static final long serialVersionUID = 1L;

	public TotalRevenueWithOptionals() {
	}
   
	@Id
	private int service_package_id;
	
	private BigDecimal revenue_with_optionals;
	
	private BigDecimal revenue_without_optionals;

	public int getService_package_id() {
		return service_package_id;
	}

	public void setService_package_id(int service_package_id) {
		this.service_package_id = service_package_id;
	}

	public BigDecimal getRevenue_with_optionals() {
		return revenue_with_optionals;
	}

	public void setRevenue_with_optionals(BigDecimal revenue_with_optionals) {
		this.revenue_with_optionals = revenue_with_optionals;
	}

	public BigDecimal getRevenue_without_optionals() {
		return revenue_without_optionals;
	}

	public void setRevenue_without_optionals(BigDecimal revenue_without_optionals) {
		this.revenue_without_optionals = revenue_without_optionals;
	}
	
	
	
}
