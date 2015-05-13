//the selection class and all of it's relevant attributes. intended to make sql queries easier. not used much.

package database_testing;

public class Selection {
	private String table;
	private String column;
	private String entry;
	
	protected String getTable(){
		return this.table;
	}
	
	protected String getColumn(){
		return this.column;
	}
	
	protected String getEntry(){
		return this.entry;
	}
	
	protected void setTable(String t){
		this.table = t;
	}
	
	protected void setColumn(String t){
		this.column = t;
	}
	
	protected void setEntry(String t){
		this.entry = t;
	}
	
	public Selection(String t,String c, String e){
		setTable(t);
		setColumn(c);
		setEntry(e);
	}
}
