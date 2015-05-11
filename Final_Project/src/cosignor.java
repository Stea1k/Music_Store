

public class cosignor {
	private String cosignorName;
	private String cosignorPhone;
	private Double cosignorOwed;
	
	protected String getCosignorName(){
		return this.cosignorName;
	}
	protected String getCosignorPhone(){
		return this.cosignorPhone;
	}
	protected Double getCosignorOwed(){
		return this.cosignorOwed;
	}
	protected void setCosignorName(String cN){
		this.cosignorName = cN;
	}
	protected void setCosignorPhone(String cP){
		this.cosignorName = cP;
	}
	protected void setCosignorOwed(Double cP){
		this.cosignorOwed = cP;
	}
	public cosignor(String cName, String cPhone){
		this.setCosignorName(cName);
		this.setCosignorPhone(cPhone);
		this.setCosignorOwed(0.0);
	}
}