package output;

import java.util.HashMap;

import databaseControl.DatabaseConnection;

public class DatabaseOutput implements IOutput {
	
	private DatabaseConnection dbcon;
	private StringBuilder sb;
	
	public DatabaseOutput(){
		dbcon = new DatabaseConnection("oracle-targetdb");
	}

	@Override
	public void setCode(HashMap<String, String> codes) {
		for(String code: codes.values()){
			System.out.println(code);
			sb.append(code + "\n\n");			
		}

	}

	@Override
	public boolean doOutput(String outputLocation) {
		System.out.println(sb.toString());
		if(!dbcon.executeQuery(sb.toString())) {
			return false;
		}
		return true;
	}

}
