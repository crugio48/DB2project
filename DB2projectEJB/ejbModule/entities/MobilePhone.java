package entities;

import java.io.Serializable;
import java.math.BigDecimal;

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
	
	public MobilePhone(int n_minutes, int n_sms, BigDecimal fee_extra_minutes, BigDecimal fee_extra_sms) {
		this.n_minutes = n_minutes;
		this.n_sms = n_sms;
		this.fee_extra_minutes = fee_extra_minutes;
		this.fee_extra_sms = fee_extra_sms;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mobile_phone_id;

	private int n_minutes;

	private int n_sms;

	private BigDecimal fee_extra_minutes;

	private BigDecimal fee_extra_sms;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private Service serviceMobilePhone;
	
	public int getMobile_phone_id() {
		return mobile_phone_id;
	}

	public void setMobile_phone_id(int mobile_phone_id) {
		this.mobile_phone_id = mobile_phone_id;
	}

	public int getN_minutes() {
		return n_minutes;
	}

	public void setN_minutes(int n_minutes) {
		this.n_minutes = n_minutes;
	}

	public int getN_sms() {
		return n_sms;
	}

	public void setN_sms(int n_sms) {
		this.n_sms = n_sms;
	}

	public BigDecimal getFee_extra_minutes() {
		return fee_extra_minutes;
	}

	public void setFee_extra_minutes(BigDecimal fee_extra_minutes) {
		this.fee_extra_minutes = fee_extra_minutes;
	}

	public BigDecimal getFee_extra_sms() {
		return fee_extra_sms;
	}

	public void setFee_extra_sms(BigDecimal fee_extra_sms) {
		this.fee_extra_sms = fee_extra_sms;
	}

	public Service getServiceMobilePhone() {
		return serviceMobilePhone;
	}

	public void setServiceMobilePhone(Service serviceMobilePhone) {
		this.serviceMobilePhone = serviceMobilePhone;
	}
	
}
