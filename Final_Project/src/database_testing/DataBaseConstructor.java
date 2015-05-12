package database_testing;

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
				"create table USERS ("
				+ "userID integer not null generated always as identity (start with 1, increment by 1), "
				+ "userName varchar(20) not null, "
				+ "userAuth integer not null with default 1, "
				+ "userPass varchar(20) not null, "
				+ "userPhone varchar(10) not null,"
				+ "userEmail varchar(20),"
				+ "primary key(userID))");
		sqlQueries.add(createTable);
		setTableToCreate(
				"create table COSIGNOR"
				+ "("
				//big thanks to Denise Michelle deBando on programming Journal-dmdb.blogspot.com
				+ "cosignorID integer not null generated always as identity (start with 1, increment by 1),"
				//end of used code
				+ "cosignorName varchar(20) not null,"
				+ "cosignorPhone varchar(20) not null,"
				+ "Primary key(cosignorID))");
		sqlQueries.add(createTable);
		setTableToCreate(
				"create table RECORDS"
				+ "("
				+ "musicID integer not null generated always as identity (start with 1, increment by 1),"
				+ "title varchar(20) not null,"
				+ "price numeric not null,"
				+ "dateAdded timestamp,"
				+ "cosignorID int not null,"
				+ "Primary Key (musicID))");
		sqlQueries.add(createTable);
		setTableToCreate(
				"create table SALES"
				+ "("
				+ "salesID integer not null generated always as identity (start with 1, increment by 1),"
				+ "date DATE,"
				+ "musicID integer not null,"
				+ "userID integer not null,"
				+ "Primary key(salesID))");
		sqlQueries.add(createTable);
	}
	
	public static void createDataTables(){
		try {
			DataConnect("root","p4ssw0rd");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int x=0; x < sqlQueries.size();x ++){
			try{
				sqlCom = conn.createStatement();
				String tableGenerator = sqlQueries.get(x);
				Object Table = sqlCom.executeUpdate(tableGenerator);
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
