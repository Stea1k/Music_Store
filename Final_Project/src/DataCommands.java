

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
	private static String dbName = "Music";
	
	//root
	private static String USER;
	//p4ssw0rd
	private static String PASS;
	
	private static boolean loggedIn = false;
	
	protected static boolean getLoggedIn(){
		return loggedIn;
	}
	
	protected static void toggleLoggedIn(){
		if(loggedIn){
			loggedIn = false;
		}else{loggedIn = true;}
	}
	
	//Presets artist and title selections on the music viewer to false.
	//When the checkbox is selected, the switch will initiate.
	
	protected static boolean artist = false;
	protected static boolean title = false;
	
	protected void switchArtist(){
		if(artist){
			artist=false;
		}else{
			artist = true;
		}
	}
	protected void switchTitle(){
		if(title){
			title=false;
		}else{
			title = true;
		}
	}
	protected static void setUser(String User){
		USER = User;
	}
	protected static void setPass(String Pass){
		PASS = Pass;
	}
	public static void dropAll(){
		try{
			sqlCom = conn.createStatement();
			Object drop = sqlCom.executeQuery(
					"DROP DATABASE MUSIC"
					);
			sqlCom.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
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
			loggedIn = true;
			}catch(SQLException e){
				e.printStackTrace(System.err);
			}
		}catch(SQLException e){
			e.printStackTrace(System.err);
		}
	}	
	//Login command for logging into software as a given user.
	//TODO How does this differ from getting a connection?
	
	public boolean login(String User, String Pass) throws SQLException{
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

	public static boolean newLogin(Users user){
		boolean success = false;
		try{
			sqlCom = conn.createStatement();
			sqlCom.executeQuery(
					"INSERT INTO USERS VALUES"
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
	public static void newCosigner(Cosigner co){
		try{
			sqlCom = conn.createStatement();
			sqlCom.executeQuery(
					"INSERT INTO COSIGNOR VALUES"
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
//	public void addMusicRecords()
	public void addMusicRecords(File file){
		try{
			sqlCom = conn.createStatement();
			sqlCom.executeQuery(
					"LOAD DATA INFILE "
					+file
					+" INTO TABLE RECORDS"
					);
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	public static DefaultTableModel searchRecords(String entry) throws SQLException{
		DefaultTableModel def = new DefaultTableModel();
		try{
			sqlCom = conn.createStatement();
			ResultSet dataFromRecords = null;
			if(artist && !title){
				dataFromRecords = sqlCom.executeQuery(
						"SELECT title,artist,price "
						+ "FROM RECORDS WHERE artist LIKE ("+entry+")");
			}else if(!artist && title){
				dataFromRecords = sqlCom.executeQuery(
						"SELECT title,artist,price "
						+ "FROM RECORDS WHERE title LIKE ("+entry+")");
			}else if(artist && title){
				dataFromRecords = sqlCom.executeQuery(
						"SELECT title,artist,price "
						+ "FROM RECORDS WHERE title LIKE ("+entry+")"
								+ " OR artist LIKE ("+entry+")");
			}
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
}
