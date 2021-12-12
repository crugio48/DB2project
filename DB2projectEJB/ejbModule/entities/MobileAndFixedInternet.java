package entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "mobile_and_fixed_internet", schema = "db2data")
public class MobileAndFixedInternet implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MobileAndFixedInternet() {
		super();
	}
	
	public MobileAndFixedInternet(int n_giga, BigDecimal fee_extra_giga) {
		this.n_giga = n_giga;
		this.fee_extra_giga = fee_extra_giga;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int internet_id;

	private int n_giga;

	private BigDecimal fee_extra_giga;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private Service serviceInternet;

	public int getInternet_id() {
		return internet_id;
	}

	public void setInternet_id(int internet_id) {
		this.internet_id = internet_id;
	}

	public int getN_giga() {
		return n_giga;
	}

	public void setN_giga(int n_giga) {
		this.n_giga = n_giga;
	}

	public BigDecimal getFee_extra_giga() {
		return fee_extra_giga;
	}

	public void setFee_extra_giga(BigDecimal fee_extra_giga) {
		this.fee_extra_giga = fee_extra_giga;
	}

	public Service getServiceInternet() {
		return serviceInternet;
	}

	public void setServiceInternet(Service serviceInternet) {
		this.serviceInternet = serviceInternet;
	}
   
}
