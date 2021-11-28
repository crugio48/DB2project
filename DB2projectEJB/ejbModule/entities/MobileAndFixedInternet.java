package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MobileAndFixedInternet
 *
 */
@Entity
@Table(name = "mobile_and_fixed_internet", schema = "db2data")
public class MobileAndFixedInternet implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MobileAndFixedInternet() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int internet_id;

	int n_giga;

	int fee_extra_giga;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	Service serviceInternet;
   
}
