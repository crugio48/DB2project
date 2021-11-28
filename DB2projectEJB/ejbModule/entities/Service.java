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
	int service_id;
	
	String type;

	@OneToOne(mappedBy = "serviceMobilePhone")
	MobilePhone mobilePhone;
	

	@OneToOne(mappedBy = "serviceInternet")
	MobileAndFixedInternet mobileAndFixedInternet;
	

	@ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
	Collection<ServicePackage> servicePackages;

   
}
