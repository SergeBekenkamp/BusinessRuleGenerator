package output;

import java.util.HashMap;

import databaseControl.DatabaseConnection;

public class DatabaseOutput implements IOutput {
	
	private DatabaseConnection dbcon;
	HashMap<String, String> codes;
	
	public DatabaseOutput(){
		dbcon = new DatabaseConnection("oracle-targetdb");
	}

	@Override
	public void setCode(HashMap<String, String> codes) {
		this.codes = codes;

	}

	@Override
	public boolean doOutput(String outputLocation) {
		dbcon.connect();
		for (String trigger : codes.values()) {
			System.out.println(trigger);
			if(!dbcon.executeQuery(trigger)) {
				dbcon.closeConnection();
				return false;
			}
		}
		dbcon.closeConnection();
		return true;
	}

}
