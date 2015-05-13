//A variety of data commands. Most have not been tested.

package database_testing;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DataCommands {
	private String dbURL = "";
	protected static Statement sqlCom = null;
	protected static Connection conn = null;
	
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";
	private static String dbName = "MUSIC";
	
	//root
	private static String USER;
	//p4ssw0rd
	private static String PASS;
	
	private static boolean loggedIn = false;
	
	protected static boolean getLoggedIn(){
		return loggedIn;
	}
	
	//meant to switch logged in constant between true and false. Mostly just caused a lot of bugs.
	protected static void toggleLoggedIn(){
		if(loggedIn){
			loggedIn = false;
		}else{loggedIn = true;}
	}
	
	protected static void setLoggedInTrue(){
		if(!loggedIn){
			loggedIn = true;
		}
	}
	
	protected static void setLoggedInFalse(){
		if(loggedIn){
			loggedIn = false;
		}
	}
	
	//Presets artist and title selections on the music viewer to false.
	protected static void setUser(String User){
		USER = User;
	}
	protected static void setPass(String Pass){
		PASS = Pass;
	}
	//Sets up connection to derby database with USER and PASS
	public static void DataConnect(String User, String Pass) throws ClassNotFoundException{
		try{
			setUser(User);
			setPass(Pass);
			Class.forName(driver);
			conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
			try{
			sqlCom = conn.createStatement();
			System.out.println("connection established");
			setLoggedInTrue();
			}catch(SQLException e){
				e.printStackTrace(System.err);
			}
		}catch(SQLException e){
			e.printStackTrace(System.err);
		}
	}	
	//meant to compare the database of users against the inputs.
	public static boolean login(String User, String Pass) throws SQLException{
		ResultSet dataFromTable = null;
		boolean found = false;
		try{
			sqlCom = conn.createStatement();
			dataFromTable = sqlCom.executeQuery(
					"SELECT * "
					+ "FROM USERS "
					+ "WHERE UserName="+"'"+User+"'"
					+ " and UserPass="+"'" +Pass+"'"
					);
			sqlCom.close();
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		if(dataFromTable.next()){
			found = true;
		}
		dataFromTable.close();
		return found;
	}

	//adds a new user to the database. MIGHT work.
	public static boolean newLogin(Users user){
		boolean success = false;
		try{
			sqlCom = conn.createStatement();
			sqlCom.executeQuery(
					"insert into USERS values"
					+"("
					+user.getUserName()+","
					+user.getUserType()+","
					+user.getUserPass()+","
					+user.getUserPhone()+","
					+user.getUserEmail()							
					+")");
			sqlCom.close();
			try{
				sqlCom = conn.createStatement();
				sqlCom.executeQuery("Call SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY"
						+ "(derby.user."+user.getUserName()+","+user.getUserPass()+")");
				sqlCom.close();
				success = true;
			}catch(Exception e){
				e.printStackTrace(System.err);
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		return success;
	}
	//adds a new cosignor to the databse.
	public static void newCosignor(Cosignor co){
		try{
			sqlCom = conn.createStatement();
			sqlCom.executeQuery(
					"insert into COSIGNOR values"
					+ "("
					+ co.getName()+","
					+ co.getPhone()+","
					+ ")"
					);
			sqlCom.close();
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	//adds new music records to the database. There is nothing that applies the ID of the cosignor, so
	//this is incomplete.
	public void addMusicRecords(File file){
		try{
			sqlCom = conn.createStatement();
			sqlCom.executeQuery(
					"load data infile "
					+file
					+" into table RECORDS"
					);
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	//returns all columns from a given table. sends them to the tableOptions Jcombobox
	public static ArrayList<String> getTableCols(String table) throws SQLException{
		DefaultTableModel def = new DefaultTableModel();
		ArrayList<String> cNames = new ArrayList<String>();
		sqlCom = conn.createStatement();
		ResultSet getTable = sqlCom.executeQuery(
				"select * from "+table);
		ResultSetMetaData rsmd = getTable.getMetaData();
		int cCount = rsmd.getColumnCount();
		for(int c = 1; c < cCount; c ++){
			cNames.add(rsmd.getColumnName(c));
		}
		sqlCom.close();
		return cNames;
	}
	
	//the default search query. MIGHT work. needs data to compare against, unfortunately.
	public static DefaultTableModel searchRecords(String Table, String col, String entry) throws SQLException{
		DefaultTableModel def = new DefaultTableModel();
		try{
			sqlCom = conn.createStatement();
			ResultSet dataFromRecords = null;
			
			String query = "select * from "+Table;
			if(entry != null){
				query += " where "+col+" like "+"%"+entry+"%";
			}
			
			dataFromRecords = sqlCom.executeQuery(query);
			
			//Ideas for this came from Paul Vargas (Simplest Code to populate JTable from result set, Stackoverflow)
			ResultSetMetaData rsmd = dataFromRecords.getMetaData();
			
			//table metadata constructing the table.
			Vector<String> columnNames = new Vector<String>();
		    int columnCount = rsmd.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(rsmd.getColumnName(column));
		    }
		    //data populating the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (dataFromRecords.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(dataFromRecords.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		    //end of Paul Vargas
			/////////////////////////////////////////////////////////////////////
		    def = new DefaultTableModel(data, columnNames);
			dataFromRecords.close();
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		sqlCom.close();
		return def;
	}
	//unlike the default, this has a preset expected result.
	//checks for music that has been around for longer than 30 days.
	public static DefaultTableModel getbargains(){
		DefaultTableModel def = new DefaultTableModel();
		try{
			sqlCom.getConnection();
			ResultSet bargains = sqlCom.executeQuery(
					"select * from RECORDS join SALES "
					+ "on RECORDS.musicID = SALES.musicID "
					+ "where timestampdiff(SQL_TSI_DAY,dateAdded,date) > 30"
					);
			//Ideas for this came from Paul Vargas (Simplest Code to populate JTable from result set, Stackoverflow)
			ResultSetMetaData rsmd = bargains.getMetaData();
			
			//table metadata constructing the table.
			Vector<String> columnNames = new Vector<String>();
		    int columnCount = rsmd.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(rsmd.getColumnName(column));
		    }
		    //data populating the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (bargains.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(bargains.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		    //end of Paul Vargas
			/////////////////////////////////////////////////////////////////////
		    def = new DefaultTableModel(data, columnNames);
		    bargains.close();
		    sqlCom.close();
		}catch(SQLException e){
			e.getStackTrace();
		}
	    return def;
	}
}
