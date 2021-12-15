package beans;

import java.util.Date;
import java.math.BigDecimal;

import java.util.Map;

public class TempOrder {

	private int servicePackageId;
	private int validityPeriodId;
	private Map<Integer,Boolean>optionalsSelected;
	private BigDecimal totalAmount;
	private Date startDate;
	private boolean isNew;
	private int orderId;
	
	public TempOrder(int servicePackageId, int validityPeriodId, Map<Integer, Boolean> optionalsSelected, BigDecimal totalAmount, Date startDate) {
		this.servicePackageId = servicePackageId;
		this.validityPeriodId = validityPeriodId;
		this.optionalsSelected = optionalsSelected;
		this.totalAmount = totalAmount;
		this.startDate = startDate;
		this.isNew = true;
	}
	
	public TempOrder(int orderId) {
		this.orderId = orderId;
		this.isNew = false;
	}
	
	public int getServicePackageId() {
		return servicePackageId;
	}
	public void setServicePackageId(int servicePackageId) {
		this.servicePackageId = servicePackageId;
	}
	public int getValidityPeriodId() {
		return validityPeriodId;
	}
	public void setValidityPeriodId(int validityPeriodId) {
		this.validityPeriodId = validityPeriodId;
	}
	public Map<Integer, Boolean> getOptionalsSelected() {
		return optionalsSelected;
	}
	public void setOptionalsSelected(Map<Integer, Boolean> optionalsSelected) {
		this.optionalsSelected = optionalsSelected;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	
}
