//The Cosignor class and all of it's parts.
package database_testing;

public class Cosignor{
	private String Name;
	private String phone;
	
	protected void setName(String N){
		this.Name = N;
	}
	
	protected void setPhone(String P){
		this.phone = P;
	}
	
	protected String getName(){
		return this.Name;
	}
	
	protected String getPhone(){
		return this.phone;
	}
	
	public Cosignor(String name,String phone){
		setName(name);
		setPhone(phone);
	}
}
