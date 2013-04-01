package output;

import java.util.HashMap;

import databaseControl.DatabaseConnection;

public class DatabaseOutput implements IOutput {
	
	private DatabaseConnection dbcon;
	private HashMap<String, String> codes;
	
	public DatabaseOutput(){
		dbcon = new DatabaseConnection("oracle-targetdb");
	}

	@Override
	public void setCode(HashMap<String, String> codes) {
		this.codes = codes;

	}

	@Override
	public boolean doOutput() {
		for (String code: codes.values()) {
			System.out.println(code);
			//if(!dbcon.executeQuery(code))
				//return false;
		}
		return true;
	}

}
