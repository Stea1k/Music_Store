package database_testing;

public class Users {
	private String userName;
	private Integer userType;
	private String userPass;
	private String userPhone;
	private String email;

	/////////////////////////////////////////////////////////////////////////////////////////////
	//getters
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	protected String getUserName(){
		return this.userName;
	}
	
	protected Integer getUserType(){
		return this.userType;
	}
	
	protected String getUserPass(){
		return this.userPass;
	}
	
	protected String getUserPhone(){
		return this.userPhone;
	}
	
	protected String getUserEmail(){
		return this.email;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//setters
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	protected void setUserName(String UN){
		this.userName = UN;
	}
	
	protected void setUserType(Integer i){
		this.userType = i;
	}	
	
	protected void setUserPass(String UP){
		this.userPass = UP;
	}
	
	protected void setUserPhone(String UPh){
		this.userPhone = UPh;
	}

	protected void setUserEmail(String UE){
		if(UE == null){
			this.email = " ";
		}else this.email = UE;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public Users(String name, String pass, String phone, String ue){
		this.setUserName(name);
		this.setUserPass(pass);
		this.setUserPhone(phone);
		this.setUserEmail(ue);
	}
}
