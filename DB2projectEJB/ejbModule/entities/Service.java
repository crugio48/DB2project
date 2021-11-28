package entities;

import java.io.Serializable;
import java.util.Collection;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_id;
	
	private String type;

	@OneToOne(mappedBy = "serviceMobilePhone")
	private MobilePhone mobilePhone;
	

	@OneToOne(mappedBy = "serviceInternet")
	private MobileAndFixedInternet mobileAndFixedInternet;
	

	@ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
	private Collection<ServicePackage> servicePackages;

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
	}


	public MobileAndFixedInternet getMobileAndFixedInternet() {
		return mobileAndFixedInternet;
	}


	public void setMobileAndFixedInternet(MobileAndFixedInternet mobileAndFixedInternet) {
		this.mobileAndFixedInternet = mobileAndFixedInternet;
	}


	public Collection<ServicePackage> getServicePackages() {
		return servicePackages;
	}


	public void setServicePackages(Collection<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}
	
	
}
