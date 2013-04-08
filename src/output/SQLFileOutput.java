package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class SQLFileOutput implements IOutput {
	StringBuilder sb;
	
	public SQLFileOutput() {
		sb = new StringBuilder();
	}
	
	@Override
	public void setCode(HashMap<String, String> codes) {
		for(String code: codes.values()){
			System.out.println(code);
			sb.append(code + "\r\n");
			sb.append("/ \r\n");
		}
	}

	@Override
	public String doOutput(String s) {
		File file = new File(s + File.separator + "output");
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".sql"));
		writer.write(sb.toString());
		writer.close();
		} catch (IOException e){
			System.out.println("Something went wrong with saving the output");
			System.out.println(e);
			sb = new StringBuilder();
			return "Something went wrong with saving the output";
		}
		return sb.toString();
	}
	
}
