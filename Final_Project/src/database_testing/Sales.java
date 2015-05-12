package database_testing;

import java.text.SimpleDateFormat;

public class Sales {
	private Integer MusicID;
	private Integer UserID;
	
	protected void setMusicID(Integer i){
		this.MusicID = i;
	}
	
	protected void setUserID(Integer i){
		this.UserID = i;
	}
	
	protected Integer getMusicID(){
		return this.MusicID;
	}
	
	protected Integer getUserID(){
		return this.UserID;
	}

	public Sales(Integer m,Integer u){
		setMusicID(m);
		setUserID(u);
	}
}
