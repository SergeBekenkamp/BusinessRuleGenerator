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
	public String doOutput(String outputLocation) {
		String s = "";
		dbcon.connect();
		for (String trigger : codes.values()) {
			System.out.println(trigger);
			if(!dbcon.executeQuery(trigger)) {
				dbcon.closeConnection();
				return "failed";
			}
			s += trigger + "\r\n";
		}
		dbcon.closeConnection();
		return s;
	}

}
