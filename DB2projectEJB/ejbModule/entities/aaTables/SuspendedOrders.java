package entities.aaTables;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "aa_suspended_orders", schema = "db2data")
@NamedQuery(name = "SuspendedOrders.findAll", query = "SELECT s FROM SuspendedOrders s")
public class SuspendedOrders implements Serializable {
	private static final long serialVersionUID = 1L;

	public SuspendedOrders() {
	}
	
	@Id
	private int order_id;

	
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	
   
}
