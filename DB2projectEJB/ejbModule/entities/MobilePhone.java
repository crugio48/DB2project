package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MobilePhone
 *
 */
@Entity
@Table(name = "mobile_phone", schema = "db2data")
public class MobilePhone implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MobilePhone() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int mobile_phone_id;

	int n_minutes;

	int n_sms;

	int fee_extra_minutes;

	int fee_extra_sms;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	Service serviceMobilePhone;
	
}
