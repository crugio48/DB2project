package entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Service
 *
 */
@Entity

public class Service implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Service() {
		super();
	}
	
	public Service(String type) {
		this.type = type;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_id;
	
	private String type;

	@OneToOne(mappedBy = "serviceMobilePhone", cascade = CascadeType.PERSIST)
	private MobilePhone mobilePhone;
	

	@OneToOne(mappedBy = "serviceInternet", cascade = CascadeType.PERSIST)
	private MobileAndFixedInternet mobileAndFixedInternet;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_package_id")
	private ServicePackage myServicePackage;

	public int getService_id() {
		return service_id;
	}


	public void setService_id(int service_id) {
		this.service_id = service_id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public MobilePhone getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(MobilePhone mobilePhone) {
		this.mobilePhone = mobilePhone;
		mobilePhone.setServiceMobilePhone(this);
	}


	public MobileAndFixedInternet getMobileAndFixedInternet() {
		return mobileAndFixedInternet;
	}


	public void setMobileAndFixedInternet(MobileAndFixedInternet mobileAndFixedInternet) {
		this.mobileAndFixedInternet = mobileAndFixedInternet;
		mobileAndFixedInternet.setServiceInternet(this);
	}


	public ServicePackage getMyServicePackage() {
		return myServicePackage;
	}


	public void setMyServicePackage(ServicePackage myServicePackage) {
		this.myServicePackage = myServicePackage;
	}
	
	
	
	
	
}
