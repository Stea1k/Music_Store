package database_testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Music {
	private String title;
	private String artist;
	private Float price;
	
	protected void setTitle(String T){
		this.title = T;
	}
	
	protected void setArtist(String A){
		this.artist = A;
	}
	
	protected void setPrice(Float P){
		this.price = P;
	}
	
	protected String getTitle(){
		return this.title;
	}
	
	protected String getArtist(){
		return this.artist;
	}
	
	protected Float getPrice(){
		return this.price;
	}
	
	public Music(String t,String a, Float p){
		setTitle(t);
		setArtist(a);
		setPrice(p);
	}
}
