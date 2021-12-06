package beans;

import java.util.Map;

public class TempOrder {

	private int servicePackageId;
	private int validityPeriodId;
	private Map<Integer,Boolean>optionalsSelected;
	
	public TempOrder(int servicePackageId, int validityPeriodId, Map<Integer, Boolean> optionalsSelected) {
		this.servicePackageId = servicePackageId;
		this.validityPeriodId = validityPeriodId;
		this.optionalsSelected = optionalsSelected;
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
	
	
	
}
