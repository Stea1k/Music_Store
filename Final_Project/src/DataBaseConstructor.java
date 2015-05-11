

import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseConstructor extends DataCommands{
	 ///////////////////////////////
	//TODO create array of strings for each table and loop through them using each as a SQL statement.
	//need to cut down on redundancy.
	///////////////////////////////
	static ArrayList<String> sqlQueries = new ArrayList<String>();
	private static String createTable;
	protected static String setTableToCreate(String command){
		return createTable = command;
	}
	static ArrayList<String> practiceData = new ArrayList<String>();
	private static String dataPoint;
	protected static String setDataPoint(String data){
		return dataPoint = data;
	}
	
	protected static void assignTables(){
		setTableToCreate( 
				"CREATE TABLE USERS ("
				+ "userID int not null AUTO_INCREMENT primary key, "
				+ "userName varchar(20) not null, "
				+ "userAuth int not null DEFAULT '1', "
				+ "userPass varchar(20) not null, "
				+ "userPhone varchar(10) not null,"
				+ "userEmail varchar(20)"
				+ ")");
		sqlQueries.add(createTable);
		setTableToCreate(
				"CREATE TABLE COSIGNOR"
				+ "("
				+ "cosignorID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "cosignorName varchar(20) NOT NULL,"
				+ "cosignorPhone varchar(20) NOT NULL"
				+ ")");
		sqlQueries.add(createTable);
		setTableToCreate(
				"CREATE TABLE RECORDS"
				+ "("
				+ "musicID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "title varchar(20) NOT NULL,"
				+ "artist VARCHAR(20) NOT NULL,"
				+ "price float NOT NULL,"
				+ "dateAdded TIMESTAMP,"
				+ "cosignorID INT REFERENCES COSIGNOR(cosignorID)"
				+ ")");
		sqlQueries.add(createTable);
		setTableToCreate(
				"CREATE TABLE SALES"
				+ "("
				+ "salesID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "date DATE,"
				+ "musicID INT REFERENCES MUSIC(musicID),"
				+ "userID INT REFERENCES USERS(userID)"
				+ ")");
		sqlQueries.add(createTable);
	}
	
	public static void createDataTables(){
		try {
			DataConnect("root","p4ssw0rd");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String i: sqlQueries){
			try{
				sqlCom = conn.createStatement();
				Object Table = sqlCom.executeQuery(i);
			}catch(SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				sqlCom.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void populate(String name,ArrayList<String> data){
		try{
			DataConnect("root","p4ssw0rd");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		cosignorData();
		for(String i: practiceData){
			try{
				sqlCom = conn.createStatement();
				Object Table = sqlCom.executeQuery(
						"INSERT INTO "
						+ name
						+" VALUES "
						+ "("
						+ i
						+ ")"
						);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void cosignorData(){
		practiceData = new ArrayList<String>();
		setDataPoint("Sun Records,012-345-6789");
		practiceData.add(dataPoint);
		setDataPoint("Lion Records,987-654-3210");
		practiceData.add(dataPoint);
	}
	
	public static void MusicData(){
		
	}
}
