package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SQLOutput implements IOutput {
	
	StringBuilder sb = new StringBuilder();

	@Override
	public void addString(String output) {
		sb.append(output + "\n");
	}

	@Override
	public boolean saveOutput(String fileName) {
		File file = new File(filename + ".sql");
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(sb.toString());
		writer.close();
		} catch (IOException e){
			System.out.println("Something went wrong with saving the output");
			System.out.println(e);
			return false;
		}
		sb = new StringBuilder();
		return true;
	}
	
	
	

}
